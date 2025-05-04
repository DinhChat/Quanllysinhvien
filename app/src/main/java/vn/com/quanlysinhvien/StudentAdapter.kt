package vn.com.quanlysinhvien

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import vn.com.quanlysinhvien.model.Student

class StudentAdapter(
    private var students: List<Student>,
    private val listener: MainActivity
) : RecyclerView.Adapter<StudentAdapter.ViewHolder>() {

    interface OnStudentMenuItemClickListener {
        fun onUpdateClicked(student: Student, position: Int)
        fun onDeleteClicked(student: Student, position: Int)
        fun onCallClicked(phone: String)
        fun onEmailClicked(email: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student = students[position]
        holder.bind(student)

        holder.itemView.setOnLongClickListener { v ->
            PopupMenu(v.context, v).apply {
                inflate(R.menu.student_context_menu)
                setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.menu_update -> listener.onUpdateClicked(student, position)
                        R.id.menu_delete -> listener.onDeleteClicked(student, position)
                        R.id.menu_call -> listener.onCallClicked(student.phone)
                        R.id.menu_email -> listener.onEmailClicked(student.email)
                        else -> return@setOnMenuItemClickListener false
                    }
                    true
                }
                show()
            }
            true
        }
    }

    override fun getItemCount() = students.size

    fun updateList(newList: List<Student>) {
        students = newList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tvName)
        private val tvMssv: TextView = itemView.findViewById(R.id.tvMssv)

        fun bind(student: Student) {
            tvName.text = student.name
            tvMssv.text = student.mssv
        }
    }
}