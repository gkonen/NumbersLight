package be.bt.numberslight.ui.fragment.listfragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import be.bt.numberslight.R
import be.bt.numberslight.databinding.MainFragmentBinding
import be.bt.numberslight.model.NumberModel
import be.bt.numberslight.ui.activity.MainActivity
import be.bt.numberslight.ui.adapter.NumbersAdapter
import be.bt.numberslight.ui.adapter.VerticalSpaceItemDecoration
import be.bt.numberslight.ui.fragment.detailsfragment.DetailsFragment
import kotlinx.android.synthetic.main.main_fragment.*


/**
 * The first Fragment which will display the RecyclerView with the details if we are on tablet landscape mode
 */
class ListFragment : Fragment(), NumbersAdapter.ClickListener {

    companion object {
        fun newInstance() = ListFragment()
    }

    // The viewModel which contains data so we keep the data when a configuration change (as the rotation)
    private lateinit var viewModel: ListViewModel

    // The binding permit to access directly to the element of the XML without the method findViewByID
    private lateinit var binding: MainFragmentBinding

    // The variable which decide if the view is for two panel or not
    private var twoPanel = false

    // The observer which take action when the value change
    private val observer = Observer<ArrayList<NumberModel>> { value ->
        with(value) {
            // if null, the request was onFailure which I translate as a Network Failure
            if (this == null) {
                binding.rvList.adapter = NumbersAdapter(context!!, ArrayList(), this@ListFragment)
                Toast.makeText(this@ListFragment.context, "Network Error", Toast.LENGTH_LONG).show()
            } else {
                binding.rvList.adapter = NumbersAdapter(context!!, value, this@ListFragment)
            }
        }
    }

    // The callback to transmit data to the activity
    private var callback: CallbackToActivity? = null

    interface CallbackToActivity {
        fun saveNumber(number: NumberModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        binding.lifecycleOwner = this

        // The view is on two Panel if a FrameLayout exists inside the xml
        if (details_container != null) {
            twoPanel = true
        }

        // Setup of the RecyclerView
        with(binding.rvList) {
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(VerticalSpaceItemDecoration(15))
            adapter =
                NumbersAdapter(
                    context!!,
                    viewModel.listItem.value ?: ArrayList(),
                    this@ListFragment
                )
        }
        // Assign my observer to the variable from viewModel
        viewModel.listItem.observe(this, observer)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is CallbackToActivity) {
            try {
                callback = context as MainActivity
            } catch (e: ClassCastException) {
                throw ClassCastException(" $context must implement saveNumber ")
            }
        } else {
            throw java.lang.ClassCastException(" $context must implement saveNumber ")
        }
    }

    override fun onDetach() {
        super.onDetach()
        callback = null
    }

    override fun clikOnDetail(number: NumberModel) {
        callback!!.saveNumber(number)

        if (!twoPanel) {
            this.findNavController().navigate(R.id.mainFragment_to_detailsFragment)
        } else {
            val fragment = DetailsFragment()
            activity!!.supportFragmentManager
                .beginTransaction()
                .replace(R.id.details_container, fragment)
                .commit()
        }

    }

}
