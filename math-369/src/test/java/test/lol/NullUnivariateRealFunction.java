package lol;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.UnivariateRealFunction;

public class NullUnivariateRealFunction<T> implements UnivariateRealFunction {

	@Override
	public final double value(double x) throws FunctionEvaluationException {
		throw new NullPointerException();
	}
	
	@Override
	public String toString() {
		throw new NullPointerException();
	}
	
	@SuppressWarnings({  "rawtypes" })
	public Object assignmentTo(Object o){
		return o != null ? o : new NullUnivariateRealFunction() ;
	}

}
