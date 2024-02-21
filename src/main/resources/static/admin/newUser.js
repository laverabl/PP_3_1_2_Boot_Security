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


function addNewUser(event) {
    event.preventDefault();

    let username = document.getElementById("nameNew").value;
    let surname = document.getElementById("surnameNew").value;
    let email = document.getElementById("emailNew").value;
    let password = document.getElementById("passwordNew").value;
    let selectedRoles = Array.from(document.getElementById("rolesIdNew").selectedOptions)
        .map(option => ({
            id: option.value,
            name: 'ROLE_' + option.text
        }))

    const jsonObject = {
        username: username,
        surname: surname,
        email: email,
        password: password,
        roles: selectedRoles
    };

    console.log(jsonObject)


    // Отправляем данные на сервер
    fetch(urlNew, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        body: JSON.stringify(jsonObject)
    }).then(response => {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error(`Error: ${response.status}`);
        }
    }).then(result => {
        alert(result.message);
        formNew.reset();
        closeModal();
        getAdminPage();
    }).catch(error => {
        console.error("Error adding new user:", error);
    });
}


