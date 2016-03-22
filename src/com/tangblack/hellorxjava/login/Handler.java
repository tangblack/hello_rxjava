package com.tangblack.hellorxjava.login;

import java.util.List;

public interface Handler
{
	public void onSuccess(String response);
	public void onSuccess(List<String> response);
	public void onFailure(Exception exception);
}
