const userurl = '/api/user';

async function getUserAndRenderPage() {
    try {
        const response = await fetch(userurl);
        if (response.ok) {
            const user = await response.json();
            renderUserInformation(user);
        } else {
            alert(`Error, ${response.status}`);
        }
    } catch (error) {
        console.error('Error fetching user data:', error);
    }
}


function headerUser(user) {
    let roles = '';
    user.roles.forEach(role => {
        roles += ' ';
        roles += role.name;
    });
    document.getElementById("navbar-username").innerHTML = user.email;
    document.getElementById("navbar-roles").innerHTML = roles.toString().replaceAll("ROLE_", "");
}
function renderUserInformation(user) {

    headerUser(user);
    const tableBody = document.getElementById('usertbody');
    let dataHtml = '';
    let rolesArray = [];
    for (let role of user.roles) {
        rolesArray.push(" " + role.name.toString().substring(5));
    }
    dataHtml = `<tr>
        <td>${user.id}</td>
        <td>${user.username}</td>
        <td>${user.surname}</td>
        <td>${user.password}</td>
        <td>${user.email}</td>
        <td>${rolesArray}</td>
    </tr>`;
    tableBody.innerHTML = dataHtml;
}

getUserAndRenderPage();
