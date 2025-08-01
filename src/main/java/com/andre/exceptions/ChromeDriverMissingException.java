package com.andre.exceptions;

import java.io.Serial;

public class ChromeDriverMissingException extends Exception {

	/**
	 * ChromeDriverMissingException is thrown when the ChromeDriver executable is not found in the system path.
	 */
	@Serial
	private static final long serialVersionUID = 2L;

}
