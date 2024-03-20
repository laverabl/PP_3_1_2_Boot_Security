async function getUser(id) {
    let url = "/admin/users/" + id;
    let response = await fetch(url);
    return await response.json();
}