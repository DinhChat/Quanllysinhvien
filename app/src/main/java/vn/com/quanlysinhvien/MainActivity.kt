package vn.com.quanlysinhvien

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import vn.com.quanlysinhvien.model.Student

class MainActivity : AppCompatActivity(), StudentAdapter.OnStudentMenuItemClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentAdapter
    private val updateLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            adapter.updateList(StudentDataSingleton.getStudents())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = StudentAdapter(StudentDataSingleton.getStudents(), this)
        recyclerView.adapter = adapter

        registerForContextMenu(recyclerView)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_add) {
            startActivity(Intent(this, AddStudentActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onUpdateClicked(student: Student, position: Int) {
        Intent(this, EditStudentActivity::class.java).apply {
            putExtra("student", student)
            updateLauncher.launch(this)
        }
    }

    override fun onDeleteClicked(student: Student, position: Int) {
        AlertDialog.Builder(this)
            .setTitle("Xác nhận xóa")
            .setMessage("Bạn có chắc muốn xóa sinh viên này?")
            .setPositiveButton("Có") { _, _ ->
                StudentDataSingleton.deleteStudent(student)
                adapter.updateList(StudentDataSingleton.getStudents())
            }
            .setNegativeButton("Không", null)
            .show()
    }

    override fun onCallClicked(phone: String) {
        Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phone")
            startActivity(this)
        }
    }

    override fun onEmailClicked(email: String) {
        Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:$email")
            startActivity(this)
        }
    }
}