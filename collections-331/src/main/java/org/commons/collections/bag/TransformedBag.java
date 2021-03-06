/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.collections.bag;

import java.util.Set;

import org.apache.commons.collections.Bag;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.collection.TransformedCollection;
import org.apache.commons.collections.set.TransformedSet;

/**
 * Decorates another <code>Bag</code> to transform objects that are added.
 * <p>
 * The add methods are affected by this class.
 * Thus objects must be removed or searched for using their transformed form.
 * For example, if the transformation converts Strings to Integers, you must
 * use the Integer form to remove objects.
 * <p>
 * This class is Serializable from Commons Collections 3.1.
 *
 * @since Commons Collections 3.0
 * @version $Revision: 1023910 $ $Date: 2010-10-18 19:11:40 +0200 (lun., 18 oct. 2010) $
 *
 * @author Stephen Colebourne
 */
public class TransformedBag<E>
        extends TransformedCollection<E> implements Bag<E> {

    /** Serialization version */
    private static final long serialVersionUID = 5421170911299074185L;

    /**
     * Factory method to create a transforming bag.
     * <p>
     * If there are any elements already in the bag being decorated, they
     * are NOT transformed.
     * Constrast this with {@link #decorateTransform}.
     * 
     * @param bag  the bag to decorate, must not be null
     * @param transformer  the transformer to use for conversion, must not be null
     * @return a new transformed Bag
     * @throws IllegalArgumentException if bag or transformer is null
     */
    public static <E> Bag<E> decorate(Bag<E> bag, Transformer<? super E, ? extends E> transformer) {
        return new TransformedBag<E>(bag, transformer);
    }
    
    /**
     * Factory method to create a transforming bag that will transform
     * existing contents of the specified bag.
     * <p>
     * If there are any elements already in the bag being decorated, they
     * will be transformed by this method.
     * Constrast this with {@link #decorate}.
     * 
     * @param bag  the bag to decorate, must not be null
     * @param transformer  the transformer to use for conversion, must not be null
     * @return a new transformed Bag
     * @throws IllegalArgumentException if bag or transformer is null
     * @since Commons Collections 3.3
     */
    public static <E> Bag<E> decorateTransform(Bag<E> bag, Transformer<? super E, ? extends E> transformer) {
        TransformedBag<E> decorated = new TransformedBag<E>(bag, transformer);
        if (transformer != null && bag != null && bag.size() > 0) {
            @SuppressWarnings("unchecked") // Bag is of type E
            E[] values = (E[]) bag.toArray();
            bag.clear();
            for(int i=0; i<values.length; i++) {
                decorated.decorated().add(transformer.transform(values[i]));
            }
        }
        return decorated;
    }

    //-----------------------------------------------------------------------
    /**
     * Constructor that wraps (not copies).
     * <p>
     * If there are any elements already in the bag being decorated, they
     * are NOT transformed.
     * 
     * @param bag  the bag to decorate, must not be null
     * @param transformer  the transformer to use for conversion, must not be null
     * @throws IllegalArgumentException if bag or transformer is null
     */
    protected TransformedBag(Bag<E> bag, Transformer<? super E, ? extends E> transformer) {
        super(bag, transformer);
    }

    /**
     * Gets the decorated bag.
     * 
     * @return the decorated bag
     */
    protected Bag<E> getBag() {
        return (Bag<E>) collection;
    }

    //-----------------------------------------------------------------------
    public int getCount(Object object) {
        return getBag().getCount(object);
    }

    public boolean remove(Object object, int nCopies) {
        return getBag().remove(object, nCopies);
    }

    //-----------------------------------------------------------------------
    public boolean add(E object, int nCopies) {
        return getBag().add(transform(object), nCopies);
    }

    public Set<E> uniqueSet() {
        Set<E> set = getBag().uniqueSet();
        return TransformedSet.<E>decorate(set, transformer);
    }

}
