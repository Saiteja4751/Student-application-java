const apiUrl = "/students";

// Load all students
function loadStudents() {
    fetch(apiUrl)
        .then(res => res.json())
        .then(data => {
            const tbody = document.querySelector("#studentTable tbody");
            tbody.innerHTML = "";
            data.forEach(student => {
                const row = `
                    <tr>
                        <td>${student.id}</td>
                        <td>${student.name}</td>
                        <td>${student.email}</td>
                        <td>${student.phone}</td>
                        <td>${student.grade}</td>
                        <td>
                            <button onclick="editStudent(${student.id}, '${student.name}', '${student.email}', '${student.phone}', '${student.grade}')">Edit</button>
                            <button onclick="deleteStudent(${student.id})">Delete</button>
                        </td>
                    </tr>
                `;
                tbody.innerHTML += row;
            });
        });
}

// Handle form submit (Add or Update)
function handleFormSubmit(e) {
    e.preventDefault(); //

    const id = document.getElementById("studentId").value;
    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const phone = document.getElementById("phone").value;
    const grade = document.getElementById("grade").value;

    const studentData = { name, email, phone, grade };
    const method = id ? "PUT" : "POST";
    const url = id ? `/students/update/${id}` : "/students/add";

    fetch(url, {
        method: method,
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(studentData)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Failed to save student");
        }
        return response.text(); // Since Spring returns String response
    })
    .then(() => {
        document.getElementById("studentForm").reset();
        document.getElementById("studentId").value = ""; // Clear hidden ID
        loadStudents();
    })
    .catch(error => console.error("Error:", error));
}

// Edit student
function editStudent(id, name, email, phone, grade) {
    document.getElementById("studentId").value = id;
    document.getElementById("name").value = name;
    document.getElementById("email").value = email;
    document.getElementById("phone").value = phone;
    document.getElementById("grade").value = grade;
}

// Delete student
function deleteStudent(id) {
    if (confirm("Are you sure you want to delete this student?")) {
        fetch(`/students/delete/${id}`, {
            method: "DELETE"
        })
        .then(() => loadStudents())
        .catch(error => console.error("Error:", error));
    }
}

// Attach event listener only after DOM is loaded
document.addEventListener("DOMContentLoaded", () => {
    loadStudents();
    document.getElementById("studentForm").addEventListener("submit", handleFormSubmit);
});
