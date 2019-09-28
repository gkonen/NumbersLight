package be.bt.numberslight.ui.fragment.listfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.bt.numberslight.factory.FactoryRepository
import be.bt.numberslight.model.NumberModel
import be.bt.numberslight.ui.activity.MainActivity

/**
 * The ViewModel will keep the data from the fragment to prevent loss of a configuration change
 */
class ListViewModel : ViewModel() {

    private var _listItem = MutableLiveData<ArrayList<NumberModel>>(ArrayList())
    val listItem: LiveData<ArrayList<NumberModel>>
        get() = _listItem

    // I retrieve the good repository by my factory determined in the activity
    private val repository = FactoryRepository.getRepository(MainActivity.repositoryName)

    init {
        // As soon the viewModel is created, the request is done
        _listItem = repository.allNumber
    }

}
