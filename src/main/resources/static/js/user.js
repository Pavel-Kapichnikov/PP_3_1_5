/*
const requestGetURL = "http://localhost:8080/user";

function sendGetRequest(method, url, body = null) {
    return fetch(url).then(response => {
        return response.json();
    });
}

sendGetRequest("GET", requestGetURL)
    .then(data => console.log(data))
    .catch(err => console.log(err));

const requestPostURL = "http://localhost:8080/admin/create";
function sendPostRequest(method, url, body = null) {
    const headers = {
        "Content-Type": "application/json"
    }
    return fetch(url, {
        method: method,
        body: JSON.stringify(body),
        headers: headers
    }).then(response => {
        if (response.ok) {
            return response.json();
        }
        return response.json().then(error => {
            throw new Error("Something went wrong");
        })
    });
}

sendGetRequest("POST", requestPostURL, body)
    .then(data => console.log(data))
    .catch(err => console.log(err));*/

fetch('/api/user/current')
    .then(response => response.json())
    .then(data => {
        updateHeader(data);
        updateTable(data);
    })
    .catch(error => console.error('Error fetching user data:', error));

function updateHeader(user) {
    const userDataBlock = document.getElementById('userHeaderData');
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
