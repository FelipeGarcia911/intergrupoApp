package felipe.garcia.testapp.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import felipe.garcia.testapp.R
import felipe.garcia.testapp.models.ProspectLogModel
import felipe.garcia.testapp.ui.OnClickListener

class ProspectLogRecyclerViewAdapter(
        private val mValues: ArrayList<ProspectLogModel>,
        private val mListener: OnClickListener?) : RecyclerView.Adapter<ProspectLogRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            mListener?.onClick(v.tag)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_prospect_log_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProspectLogRecyclerViewAdapter.ViewHolder, position: Int) {
        val item = mValues[position]
        holder.setNames(item.name, item.lastName)
        holder.setDate(item.date)
        holder.setIdentification(item.identification)
        holder.setTelephone(item.telephone)

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    fun replaceItems(list: ArrayList<ProspectLogModel>) {
        mValues.clear()
        mValues.addAll(list)
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        private val date: TextView = mView.findViewById(R.id.textView_date)
        private val names: TextView = mView.findViewById(R.id.textView_names)
        private val identification: TextView = mView.findViewById(R.id.textView_identification)
        private val telephone: TextView = mView.findViewById(R.id.textView_telephone)

        fun setNames(name: String, lasName: String) {
            names.text = name.plus(" ").plus(lasName)
        }

        fun setDate(string: String) {
            date.text = string
        }

        fun setIdentification(string: String) {
            identification.text = string
        }

        fun setTelephone(string: String) {
            telephone.text = string
        }

    }
}
