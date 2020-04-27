package vargajana95.moneytracker.ui.transactions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_transaction.view.*
import vargajana95.moneytracker.R
import vargajana95.moneytracker.model.Transaction
import kotlin.math.absoluteValue

class TransactionsAdapter(private val context: Context, private var transactions: List<Transaction>) : RecyclerView.Adapter<TransactionsAdapter.ViewHolder>() {

            override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
            val itemView = LayoutInflater.from(context).inflate(R.layout.card_transaction, viewGroup, false)
            return ViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val transaction = transactions[position]

//            transaction.images?.let {
//                val images = transaction.images!!
//                if (images.isNotEmpty()) {
//                    Glide.with(context).load(images[0].url).into(holder.ivImage)
//                }
//            }

            holder.tvName.text = transaction.name
            holder.tvAmount.text = context.getString(R.string.amount, transaction.amount.absoluteValue)
            holder.tvAmount.setTextColor(ContextCompat.getColor(context, if(transaction.amount >= 0) R.color.income_green else R.color.outflow_red))
            holder.tvCategory.text = transaction.category.name
            holder.tvDate.text = transaction.date
            holder.ivImage.setImageDrawable(ContextCompat.getDrawable(context, if (transaction.amount >= 0) R.drawable.ic_income else R.drawable.ic_outflow))
        }

        override fun getItemCount() = transactions.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var ivImage: ImageView = view.ivImage
        var tvName: TextView = view.tvName
        var tvAmount: TextView = view.tvAmount
        var tvDate: TextView = view.tvDate
        var tvCategory: TextView = view.tvCategory
    }
}