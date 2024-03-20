const url  = "api/user"

function fillInPrincipal() {
    fetch(url, {
        credentials: 'same-origin'
    })
        .then(res => res.json())
        .then(data => {
            $('#headerUserName').append(data.username);
            let roles = data.roles.map(role => " " + role.role.toString());
            $('#headerRole').append(roles);
            let user = `<tr>
            <td>${data.id}</td>
            <td>${data.username}</td>
            <td>${data.password}</td>
            <td>${roles}</td>
        </tr>`;
            $('#userTable').append(user);
        })
        .catch(error => console.error('Ошибка при получении информации о пользователе:', error));
}

fillInPrincipal();