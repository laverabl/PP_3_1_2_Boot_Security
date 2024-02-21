const adminurl = '/api/admin';

async function getAdminPage() {
    try {
        let page = await fetch(adminurl);

        if (page.ok) {
            let listAllUser = await page.json();
            loadTableData(listAllUser);
            headerUser(listAllUser);
        } else {
            alert(`Error, ${page.status}`);
        }
    } catch (error) {
        console.error("Error fetching data:", error);
    }
}

function headerUser(listAllUser) {
    if (listAllUser.length > 0) {
        let user = listAllUser[0];
        let roles = '';
        user.roles.forEach(role => {
            roles += ' ';
            roles += role.name;
        });
        document.getElementById("navbar-username").innerHTML = user.email;
        document.getElementById("navbar-roles").innerHTML = roles.toString().replaceAll("ROLE_", "");
    }
}

function loadTableData(listAllUser) {
    const tableBody = document.getElementById('adminTBody');
    let dataHtml = '';

    for (let user of listAllUser) {
        let roles = user.roles.map(role => role.name.replace("ROLE_", "")).join(", ");

        dataHtml +=
            `<tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.surname}</td>
                <td>${user.email}</td>
                <td>${roles}</td>
                <td>
                    <button type="button" class="btn btn-primary" onclick="" >Edit</button>
                </td>
                <td>
                    <button class="btn btn-danger" onclick=" ">Delete</button>
                </td>
            </tr>`;
    }

    tableBody.innerHTML = dataHtml;
}

// Вызов функции для получения данных и отображения таблицы
getAdminPage();
