const urlNew = '/api/admin/newUser';

function show(showId, hideId) {
    document.getElementById(showId).style.display = "block";
    document.getElementById(hideId).style.display = "none";
    if (showId === "newUser") {
        document.getElementById("newUserModal").style.display = "block";
    }
    return false;
}
function closeModal() {
    document.getElementById('newUserModal').style.display = 'none';
    document.getElementById("tabUsers").style.display = 'block';
}

async function addNewUser(event) {
    event.preventDefault();

    const form = document.getElementById('addForm');

    const jsonObject = {
        username: form.username.value,
        surname: form.surname.value,
        email: form.email.value,
        password: form.password.value,
        roles: Array.from(form.roles.selectedOptions, option => option.value)
    };

    try {
        const response = await fetch(urlNew, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: JSON.stringify(jsonObject)
        });

        if (response.ok) {
            let result = await response.json();
            alert(result.message);
            await getAdminPage();
            closeModal();
        } else {
            alert(`Error, ${response.status}`);
        }
    } catch (error) {
        console.error("Error adding new user:", error);
    }
}

