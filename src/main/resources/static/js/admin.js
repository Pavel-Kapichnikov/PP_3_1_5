/*<tr th:each="user : ${users}" th:class="${user.getUsername() == currentUser.getUsername()} ? 'table-active' : ''">
    <td><button  class="btn btn-info btn-sm" data-toggle="modal" th:data-target="'#editModal' + ${user.getId()}">Edit</button></td>
    <td><button class="btn btn-danger btn-sm" data-toggle="modal" th:data-target="'#deleteModal' + ${user.getId()}">Delete</button></td>
</tr>*/

const currentPath = window.location.pathname;
let currentUser;

document.addEventListener('DOMContentLoaded', async function () {
    await loadCurrentUser();
    updateHeader(currentUser);
    updateTable(currentUser);
});

async function loadCurrentUser() {
    try {
        const response = await fetch('/api/admin/current');
        currentUser = await response.json();
    } catch (error) {
        console.error('Error fetching user data:', error);
    }
}

function updateHeader(user) {
    const userDataBlock = document.getElementById('headerData');
    userDataBlock.innerHTML = `
                <span class="font-weight-bold">${user.username}</span> with roles:
                <span>${user.roles.join(' ')}</span>
                `;
}

function updateTable(user) {
    const tableBody = document.getElementById('userTableBody');
    tableBody.innerHTML = '';

    let row = `<tr class="table-active">
                  <td>${user.id}</td>
                  <td>${user.name}
                  <td>${user.lastName}</td>
                  <td>${user.age}</td>
                  <td>${user.username}</td>
                  <td>${user.roles.join(' ')}</td>
                  </tr>`;

    tableBody.insertAdjacentHTML('beforeend', row);
}