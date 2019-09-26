package be.bt.numberslight.model

import com.google.gson.annotations.SerializedName

/**
 * The model of the object I receive from the server
 *
 * @param name The name of the number
 * @param url The url of the image to display
 */
open class NumberModel(name: String, url: String) {

    @SerializedName("name")
    open var name = name
    @SerializedName("image")
    open var url = url

    override fun toString(): String {
        return "NumberModel(name='$name', url='$url')"
    }
}

/**
 * The second model of the object which give more detail of the object
 *
 * @param name The name of the number
 * @param text The name in Japanese of the number
 * @param url The url of the image to display
 */
class NumberDetailModel(name: String, text: String, url: String) : NumberModel(name, url) {

    @SerializedName("text")
    var text = text

    override fun toString(): String {
        return "NumberDetailModel(name='$name', text='$text', url='$url')"
    }


}