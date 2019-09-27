package be.bt.numberslight.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

import be.bt.numberslight.R
import be.bt.numberslight.databinding.NumbersListItemBinding
import be.bt.numberslight.model.NumberModel

import com.squareup.picasso.Picasso

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

    // I create companion object to keep in memory which one is selected
    companion object {
        var selectedPos = RecyclerView.NO_POSITION
    }

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

        if (listItem[position].url != "") {
            Picasso.get().load(listItem[position].url).into(holder.binding.imageView)
        }

    }


    inner class MyViewHolder(
        val binding: NumbersListItemBinding,
        private val callback: ClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            this.binding.clItem.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {

                    notifyItemChanged(selectedPos)
                    selectedPos = adapterPosition
                    notifyItemChanged(selectedPos)

                    callback.clikOnDetail(binding.number!!)
                }

            }
        }

    }

}

