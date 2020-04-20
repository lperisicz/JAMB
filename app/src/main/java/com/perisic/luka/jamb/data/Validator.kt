package com.perisic.luka.jamb.data

interface Validator {

    fun validate(numbers: String): Int?

}

//regex, multiple(with field)
class RegexValidator(
    regexString: String
) : Validator {

    private val regex = regexString.toRegex()

    override fun validate(numbers: String): Int? {
        regex.find(numbers)?.value?.let {
            return it.toCharArray().sumBy { char -> char.toString().toInt() }
        }
        return null
    }
}