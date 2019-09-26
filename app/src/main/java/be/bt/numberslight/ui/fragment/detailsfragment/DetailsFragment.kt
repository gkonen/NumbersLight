package be.bt.numberslight.ui.fragment.detailsfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import be.bt.numberslight.R
import be.bt.numberslight.databinding.DetailsFragmentBinding
import be.bt.numberslight.model.NumberDetailModel
import be.bt.numberslight.ui.activity.MainActivity
import com.squareup.picasso.Picasso

/**
 * The second fragment which display the details of the Number
 */
class DetailsFragment : Fragment() {

    companion object {
        fun newInstance() = DetailsFragment()
    }

    // The viewModel which contains data so we keep the data when a configuration change (as the rotation)
    private lateinit var viewModel: DetailsViewModel

    // The binding permit to access directly to the element of the XML without the method findViewByID
    private lateinit var binding: DetailsFragmentBinding

    // The observer which take action when the value change
    private val observer = Observer<NumberDetailModel> { value ->
        with(value) {
            if (this == null) {
                Toast.makeText(this@DetailsFragment.context, "Networking Error", Toast.LENGTH_LONG)
                    .show()
            } else {
                Picasso.get().load(this.url).into(binding.ivDetails)
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.details_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)
        viewModel.numberPicked = (activity as MainActivity).number
        binding.lifecycleOwner = this

        // By binding the viewModel, we have all the data inside the xml and simplify the code
        binding.detailViewModel = viewModel

        viewModel.number.observe(this, observer)

    }


}
