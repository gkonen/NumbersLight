package be.bt.numberslight.ui.fragment.detailsfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.bt.numberslight.model.NumberDetailModel
import be.bt.numberslight.model.NumberModel
import be.bt.numberslight.repository.Repository

class DetailsViewModel : ViewModel() {

    private var _number = MutableLiveData<NumberDetailModel>(
        NumberDetailModel("name of Number", "Japanese Name", "url displaying image")
    )
    val number: LiveData<NumberDetailModel>
        get() = _number

    private val repository = Repository.getInstance()

    var numberPicked: NumberModel? = null
        set(value) {
            value?.let {
                field = it
                _number = repository.getDetailNumber(it.name)
            }
        }


}
