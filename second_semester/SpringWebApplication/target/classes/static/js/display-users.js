window.onload = async () => {
    await populateTable();
}

const populateTable = async () => {
    const table = document.getElementById('users');
    const res = await fetch('/get-users');
    const requests = await res.json();

    const createRow =
        (id, nameText, typeText, blocked) => {
            const row = document.createElement('tr');

            const name = document.createElement('td');
            const type = document.createElement('td');
            const actions = document.createElement('td')
            name.innerText = nameText;
            type.innerText = typeText;

            let blockButton = document.createElement("button");
            if (!blocked) {
                blockButton.innerText = "Block"
                blockButton.className = "badge badge-danger";
                blockButton.onclick = async function () {
                    await fetch(`/block-user/${id}`)
                    window.location.reload()
                }
            } else {
                blockButton.innerText = "Unblock"
                blockButton.className = "badge badge-secondary";
                blockButton.onclick = async function () {
                    await fetch(`/unblock-user/${id}`)
                    window.location.reload()
                }
            }
            actions.appendChild(blockButton)

            row.appendChild(name)
            row.appendChild(type)
            row.appendChild(actions)

            return row;
        };

    for (let request of requests) {
        const row = createRow(request.id, request.name, request.type, request.blocked);
        table.appendChild(row);
    }

}