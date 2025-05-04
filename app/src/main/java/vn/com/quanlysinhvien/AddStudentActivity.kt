package vn.com.quanlysinhvien

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import vn.com.quanlysinhvien.databinding.ActivityAddStudentBinding
import vn.com.quanlysinhvien.model.Student

class AddStudentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddStudentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Sử dụng binding để truy cập view
        binding.btnSave.setOnClickListener {
            val student = Student(
                binding.etName.text.toString(),
                binding.etMssv.text.toString(),
                binding.etEmail.text.toString(),
                binding.etPhone.text.toString()
            )
            StudentDataSingleton.addStudent(student)
            setResult(RESULT_OK)
            finish()
        }
    }
}