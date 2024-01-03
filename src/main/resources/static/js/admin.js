const currentPath = window.location.pathname;
let currentUser;
let usersList;

document.addEventListener('DOMContentLoaded', async function () {
    await loadCurrentUser();
    await loadUsersList();
    updateHeader(currentUser);
    updateAdminTable(usersList);

    const editButtons = document.querySelectorAll('.edit-btn');
    const deleteButtons = document.querySelectorAll('.delete-btn');
    editButtons.forEach(button => {
        button.addEventListener('click', editModalHandler);
    });
    deleteButtons.forEach(button => {
        button.addEventListener('click', deleteModalHandler);
    });

    updateUserTable(currentUser);
});

async function loadCurrentUser() {
    try {
        const response = await fetch('/api/admin/current');
        currentUser = await response.json();
    } catch (error) {
        console.error('Error fetching user data:', error);
    }
}

async function loadUsersList() {
    try {
        const response = await fetch('/api/admin/users');
        usersList = await response.json();
    } catch (error) {
        console.error('Error fetching users data:', error);
    }
}

function updateHeader(user) {
    const userDataBlock = document.getElementById('headerData');
    userDataBlock.innerHTML = `
                <span class="font-weight-bold">${user.username}</span> with roles:
                <span>${user.roles.join(' ')}</span>
                `;
}

function updateAdminTable(users) {
    const tableBody = document.getElementById('adminTableBody');
    tableBody.innerHTML = '';

    users.forEach(function (user) {
        let row = `<tr ${(user.username === currentUser.username) ? 'class="table-active"' : ''}>
                      <td>${user.id}</td>
                      <td>${user.name}
                      <td>${user.lastName}</td>
                      <td>${user.age}</td>
                      <td>${user.username}</td>
                      <td>${user.roles.join(' ')}</td>
                      <td><button class="btn btn-info btn-sm edit-btn" data-toggle="modal" data-target="#editModal" data-userid="${user.id}">Edit</button></td>
                      <td><button class="btn btn-danger btn-sm delete-btn" data-toggle="modal" data-target="#deleteModal" data-userid="${user.id}">Delete</button></td>
                      </tr>`;

        tableBody.insertAdjacentHTML('beforeend', row);
    });
}

function updateUserTable(user) {
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

function editModalHandler() {
    const userId = this.dataset.userid;
    console.log('Edit button clicked for user ID:', userId);
    const selectedUser = usersList.find(user => user.id == userId);

    document.getElementById('editID').value = selectedUser.id;
    document.getElementById('editName').value = selectedUser.name;
    document.getElementById('editLastName').value = selectedUser.lastName;
    document.getElementById('editAge').value = selectedUser.age;
    document.getElementById('editUsername').value = selectedUser.username;
    document.getElementById('editPassword').value = selectedUser.password;
    document.getElementsByName('editOptions').forEach(option => {
        option.selected = selectedUser.roles.includes(option.value);
    });
}

function deleteModalHandler() {
    const userId = this.dataset.userid;
    console.log('Delete button clicked for user ID:', userId);
    const selectedUser = usersList.find(user => user.id == userId);

    document.getElementById('delID').value = selectedUser.id;
    document.getElementById('delName').value = selectedUser.name;
    document.getElementById('delLastName').value = selectedUser.lastName;
    document.getElementById('delAge').value = selectedUser.age;
    document.getElementById('delUsername').value = selectedUser.username;
    document.getElementsByName('deleteOptions').forEach(option => {
        option.selected = selectedUser.roles.includes(option.value);
    });
}

document.getElementById('editBtn').addEventListener('click', function () {
    //event.preventDefault();

    const form = document.getElementById('editForm');
    let userDTO = {
        id: document.getElementById('editID').value,
        firstname: document.getElementById('editName').value,
        lastname: document.getElementById('editLastName').value,
        age: document.getElementById('editAge').value,
        username: document.getElementById('editUsername').value,
        password: document.getElementById('editPassword').value,
        roles: Array.from(document.getElementById('editRoleSelect').selectedOptions).map(option => "ROLE_" + option.value)
    };

    const requestOptions = {
        method: 'POST',
        body: JSON.stringify(userDTO),
        headers: {
            "Content-Type": "application/json",
        },
    };

    fetch('api/admin/edit', requestOptions)
        .then(response => {
            if (response.ok) {
                console.log('Form data submitted successfully!');
            } else {
                console.error('Error submitting form data:', response.statusText);
            }
        })
        .catch(error => {
            console.error('Error submitting form data:', error);
        });
});