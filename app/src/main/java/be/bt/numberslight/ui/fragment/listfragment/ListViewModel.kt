package be.bt.numberslight.ui.fragment.listfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.bt.numberslight.model.NumberModel
import be.bt.numberslight.repository.Repository

/**
 * The ViewModel will keep the data from the fragment to prevent loss of a configuration change
 */
class ListViewModel : ViewModel() {

    private var _listItem = MutableLiveData<ArrayList<NumberModel>>(ArrayList())
    val listItem: LiveData<ArrayList<NumberModel>>
        get() = _listItem

    // The repository will retrieve the data to separate clearly the role
    private val repository = Repository.getInstance()

    init {
        // As soon the viewModel is created, the request is done
        _listItem = repository.allNumber
    }

}
