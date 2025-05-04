package vn.com.quanlysinhvien

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import vn.com.quanlysinhvien.model.Student
import vn.com.quanlysinhvien.databinding.ActivityEditStudentBinding


class EditStudentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditStudentBinding
    private lateinit var originalStudent: Student

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        originalStudent = intent.getParcelableExtra("student")!!
        showCurrentData()

        binding.btnSave.setOnClickListener {
            val updatedStudent = originalStudent.copy(
                name = binding.etName.text.toString(),
                mssv = binding.etMssv.text.toString(),
                email = binding.etEmail.text.toString(),
                phone = binding.etPhone.text.toString()
            )
            StudentDataSingleton.updateStudent(updatedStudent)
            setResult(RESULT_OK)
            finish()
        }
    }

    private fun showCurrentData() {
        binding.etName.setText(originalStudent.name) // Sử dụng binding
        binding.etMssv.setText(originalStudent.mssv)
        binding.etEmail.setText(originalStudent.email)
        binding.etPhone.setText(originalStudent.phone)
    }
}