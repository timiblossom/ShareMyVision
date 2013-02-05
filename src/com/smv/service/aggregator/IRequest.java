package com.smv.service.aggregator;

import java.util.concurrent.Callable;

/**
 * @author Minh Do
 * 03/01/2010
 */
public interface IRequest<T> extends Callable<T> {
	public boolean validate();
	public String execute() throws Exception;
}
