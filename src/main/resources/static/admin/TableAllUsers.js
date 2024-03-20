const table = $('#usersTable');
findAll();

function findAll() {
    table.empty()
    fetch("/admin/users")
        .then(res => res.json())
        .then(data => {
            data.forEach(user => {
                let usersTable = `$(
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.username}</td>
                            <td>${user.password}</td>
                            <td>${user.roles.map(role => role.role.toString())}</td>
                            <td>
                                <button type="button" class="btn btn-primary"
                                data-bs-toogle="modal"
                                data-bs-target="#editModal"
                                onclick="editModalData(${user.id})">Edit</button>
                            </td>
                            <td>
                                <button type="button" class="btn btn-danger" 
                                data-toggle="modal"
                                data-bs-target="#deleteModal"
                                onclick="deleteModalData(${user.id})">Delete</button>
                            </td>
                        </tr>)`;
                table.append(usersTable);
            })
        })
}