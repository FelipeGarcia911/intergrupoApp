package felipe.garcia.testapp.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import felipe.garcia.testapp.R
import felipe.garcia.testapp.models.ProspectModel
import felipe.garcia.testapp.ui.OnClickListener

class ProspectListRecyclerViewAdapter(
        private val mValues: ArrayList<ProspectModel>,
        private val mListener: OnClickListener?) : RecyclerView.Adapter<ProspectListRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            mListener?.onClick(v.tag)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_prospect_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.setNames(item.name, item.lastName)
        holder.setIdentification(item.identification)
        holder.setTelephone(item.telephone)
        holder.setStatus(item.status)

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    fun replaceItems(list: ArrayList<ProspectModel>) {
        mValues.clear()
        mValues.addAll(list)
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        private val names: TextView = mView.findViewById(R.id.textView_date)
        private val identification: TextView = mView.findViewById(R.id.textView_names)
        private val telephone: TextView = mView.findViewById(R.id.textView_lastName)
        private val status: TextView = mView.findViewById(R.id.textView_status)

        fun setNames(name: String, lasName: String) {
            names.text = name.plus(" ").plus(lasName)
        }

        fun setIdentification(string: String) {
            identification.text = string
        }

        fun setTelephone(string: String) {
            telephone.text = string
        }

        fun setStatus(string: Int) {
            status.text = when (string) {
                0 -> "Pending"
                1 -> "Approved"
                2 -> "Accepted"
                3 -> "Rejected"
                else -> {
                    "Undefined"
                }
            }
        }

    }
}
