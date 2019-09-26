package be.bt.numberslight.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import be.bt.numberslight.R
import be.bt.numberslight.databinding.NumbersListItemBinding
import be.bt.numberslight.model.NumberModel
import com.squareup.picasso.Picasso
import java.lang.ref.WeakReference

/**
 * The class which implement the adapter of the RecyclerView which will display the data
 * @property context will be used to keep a trace of the context
 * @property listItem is the list of all our element which need to be displayed
 * @property callback the callback permit to link the viewHolder to the fragment
 */
class NumbersAdapter(
    private val context: Context,
    private var listItem: ArrayList<NumberModel>,
    private val callback: ClickListener
) : RecyclerView.Adapter<NumbersAdapter.MyViewHolder>() {

    // TODO : find a solution to keep the value when we change the fragment
    private var selectedPos = RecyclerView.NO_POSITION

    // The interface for the callback to link the fragment and the viewHolder
    interface ClickListener {
        fun clikOnDetail(number: NumberModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: NumbersListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.numbers_list_item,
            parent, false
        )

        return MyViewHolder(binding, callback)
    }

    override fun getItemCount() = listItem.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // I bind all the object to simplify the code
        holder.binding.number = listItem[position]

        // The item is selected if we clicked on it
        holder.binding.clItem.isSelected = (selectedPos == position)

        Picasso.get().load(listItem[position].url).into(holder.binding.imageView)

    }


    inner class MyViewHolder : RecyclerView.ViewHolder, View.OnClickListener {

        val binding: NumbersListItemBinding
        // I keep a weak reference to avoid a strong reference to the Fragment
        private val callbackWeakRef: WeakReference<ClickListener>

        constructor(
            binding: NumbersListItemBinding,
            callback: ClickListener
        ) : super(binding.root) {
            this.binding = binding
            this.callbackWeakRef = WeakReference(callback)

            this.binding.clItem.setOnClickListener(this)
            // The button are here to see the change of state on the click
            // TODO : Remove later
            this.binding.button.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            when (v!!.id) {
                R.id.cl_item -> {
                    if (adapterPosition == RecyclerView.NO_POSITION) return

                    notifyItemChanged(selectedPos)
                    selectedPos = adapterPosition
                    notifyItemChanged(selectedPos)
                }
                R.id.button -> {
                    val callback = callbackWeakRef.get()
                    callback?.let {
                        it.clikOnDetail(binding.number!!)
                    }

                }
            }
        }
    }

}

