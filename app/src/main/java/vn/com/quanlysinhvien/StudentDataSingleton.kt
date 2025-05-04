package vn.com.quanlysinhvien

import vn.com.quanlysinhvien.model.Student

object StudentDataSingleton {
    private val students = mutableListOf(
        Student("Nguyễn Văn A", "001", "a@hust.com", "0123456789"),
        Student("Trần Thị B", "002", "b@hust.com", "0987654321"),
        Student("Trần Thị C", "003", "b@hust.com", "0000000321")
    )

    fun getStudents(): List<Student> = students.toList()

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun updateStudent(updatedStudent: Student) {
        val index = students.indexOfFirst { it.mssv == updatedStudent.mssv }
        if (index != -1) {
            students[index] = updatedStudent
        }
    }

    fun deleteStudent(student: Student) {
        students.removeIf { it.mssv == student.mssv }
    }
}