let formNew = document.forms["formNewUser"];
addUser();

function addUser() {
    formNew.addEventListener("submit", ev => {
        ev.preventDefault();
        let newUserRoles = [];
        for (let i = 0; i < formNew.roles.options.length; i++) {
            if (formNew.roles.options[i].selected) {
                newUserRoles.push({
                    id: formNew.roles.options[i].value,
                    name: "ROLE_" + formNew.roles.options[i].text
                });
            }
        }
        fetch("/admin/users", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: formNew.id.value,
                username: formNew.username.value,
                password: formNew.password.value,
                roles: newUserRoles
            })
        }).then(() => {
            formNew.reset();
            findAll();
            $('#nav-home-tab').tab('show');
        });
    });
}