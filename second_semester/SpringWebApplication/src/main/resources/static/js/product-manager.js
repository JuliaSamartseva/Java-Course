window.onload = async () => {
    await populateTable();
    const pathArray = window.location.pathname.split('/');

    if (pathArray.length === 4) {
        let result = pathArray[3];
        const res = await fetch(`/get-product/${result}`);
        const request = await res.json();
        document.getElementById('product-name').value = request.name;
        document.getElementById('price').value = request.price;
        document.getElementById('description').value = request.description;
        for (let option of document.getElementById("type").options) {
            if (option.text === request.type.name)
                option.selected = true;
        }
    }
}

const populateTable = async () => {
    const select = document.getElementById('type');
    const res = await fetch('/get-product-types');
    const requests = await res.json();

    const createOption =
        (value, text) => {
            let option = document.createElement('option');
            option.setAttribute('value', value);
            option.appendChild(document.createTextNode(text));
            return option;
        };

    for (let request of requests) {
        const option = createOption(request.id, request.name)
        select.appendChild(option);
    }
}

const checkFormInputs =
    async () => {
        document.getElementById('product-submit').disabled = true;

        const data = new Map([
            ['product-name', document.getElementById('product-name').value],
            ['price', document.getElementById('price').value],
            ['description', document.getElementById('description').value],
            ['type', document.getElementById('type').value],
        ]);

        for (let [key, value] of data) {
            if (value == null) {
                formFailure(`The ${key} input is missing. Please refresh the page and try again.'`);
                return;
            }
        }

        for (let [key, value] of data) {
            data.set(key, value.trim());
            if (data.get(key) === '') {
                formFailure(`The ${key} input is empty. Please fill it out and try again.`);
                return;
            }
        }

        if (data.get('product-name').length < 3) {
            formFailure(`Product Name is too short. The minimum allowed length is 3`);
            return;
        }

        if (data.get('product-name').length > 20) {
            formFailure(`Product Name is too long. The maximum allowed length is 20.`);
            return;
        }
    }

const formFailure = (errorMessage) => {
    const modalText = document.getElementById('failureModalText');
    modalText.innerText = errorMessage;
    $('#failureModal').modal(null);
    document.getElementById('product-submit').disabled = false;
}

function findGetParameter(parameterName) {
    let result = null,
        tmp = [];
    location.search
        .substr(1)
        .split("&")
        .forEach(function (item) {
            tmp = item.split("=");
            if (tmp[0] === parameterName) result = decodeURIComponent(tmp[1]);
        });
    return result;
}