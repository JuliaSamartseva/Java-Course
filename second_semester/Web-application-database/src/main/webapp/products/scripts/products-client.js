window.onload = async () => {
    await populateTable();
}

const populateTable = async () => {
    const table = document.getElementById('products');
    const res = await fetch('../servlets/products');
    const requests = await res.json();

    const createRow =
        (nameText, priceText, descriptionText, productTypeText) => {
            const row = document.createElement('tr');

            const name = document.createElement('td');
            const price = document.createElement('td');
            const description = document.createElement('td');
            const productType = document.createElement('td');

            name.innerText = nameText;
            price.innerText = priceText;
            description.innerText = descriptionText;
            productType.innerText = productTypeText;

            row.appendChild(name);
            row.appendChild(price);
            row.appendChild(description);
            row.appendChild(productType);

            return row;
        };

    const createOption =
        (value, text, selected) => {
            let option = document.createElement('option');
            option.setAttribute('value', value);
            option.appendChild(document.createTextNode(text));
            option.selected = selected
            return option;
        };

    const cartButtons =
        (id) => {
            const actions = document.createElement('td');
            let quantity = document.createElement('select');
            quantity.appendChild(createOption(1, "Qty: 1", true))
            quantity.appendChild(createOption(2, "Qty: 2", false))
            quantity.appendChild(createOption(3, "Qty: 3", false))

            let addToCart = document.createElement("button");
            addToCart.innerText = "add to cart"
            addToCart.className = "badge badge-dark";
            addToCart.style.marginRight = "10px";
            addToCart.onclick = async function () {
                await fetch(`../servlets/products/add-to-cart?product_id=${id}&quantity=${quantity.value}`)
                alert("Added product to the cart.")
            }

            actions.appendChild(addToCart);
            actions.appendChild(quantity);

            return actions
        }

    for (let request of requests) {
        const row = createRow(request.name, request.price, request.description, request.productType);
        row.appendChild(cartButtons(request.productId));
        table.appendChild(row);
    }
};