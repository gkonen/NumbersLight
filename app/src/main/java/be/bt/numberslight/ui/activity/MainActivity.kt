package be.bt.numberslight.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import be.bt.numberslight.R
import be.bt.numberslight.model.NumberModel
import be.bt.numberslight.repository.RepositoryName
import be.bt.numberslight.ui.fragment.listfragment.ListFragment

/**
 * The activity host the different fragment which I navigate with the NavigationController
 */
class MainActivity : AppCompatActivity(), ListFragment.CallbackToActivity {

    // I give here which repository I used for the entire project
    companion object {
        val repositoryName = RepositoryName.REMOTEDATA
    }

    // This variable permit to transit data between my two fragment
    // It is not inside a viewModel as I will save it inside the viewModel of the fragment
    var number: NumberModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

    override fun saveNumber(number: NumberModel) {
        this.number = number
    }


}
