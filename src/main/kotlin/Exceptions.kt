/*
 * Copyright (c) 2022 Robert Kovats
 */

class InvalidFormatExc(message: String = "Invalid format"): Exception(message)

class EOFReachedExc(message: String = "The EOF was reached and input cannot be null"): Exception(message)
