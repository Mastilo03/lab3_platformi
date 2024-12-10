package mk.ukim.finki.lab_3.adapters
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mk.ukim.finki.lab_3.R
import mk.ukim.finki.lab_3.retrofit.Quote

class QuoteAdapter(
    private val quotes: ArrayList<Quote> = ArrayList<Quote>(),
    private val onItemClicked: (Quote) -> Unit
) : RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder>() {

    // ViewHolder holds references to item views
    class QuoteViewHolder(itemView: View, private val onItemClicked: (Quote) -> Unit) : RecyclerView.ViewHolder(itemView) {
        val idTextView: TextView = itemView.findViewById(R.id.quoteIdTextView)
        val contentTextView: TextView = itemView.findViewById(R.id.quoteContentTextView)
        val authorTextView: TextView = itemView.findViewById(R.id.quoteAuthorTextView)

        @SuppressLint("SetTextI18n")
        fun bind(quote: Quote) {

            idTextView.text = quote.id.toString();
            contentTextView.text = quote.quote;
            authorTextView.text = quote.author;

            itemView.setOnClickListener{
                onItemClicked(quote)
            }

        }

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_quote, parent, false) // Inflate the item layout
        return QuoteViewHolder(view,onItemClicked)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
       holder.bind(quotes[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = quotes.size

    fun updateQuotes(newQuotes: List<Quote>) {
        quotes.clear();
        quotes.addAll(newQuotes)
        notifyDataSetChanged()
    }
}
