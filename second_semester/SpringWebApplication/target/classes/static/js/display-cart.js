window.onload = async () => {
    document.getElementById('place-order').onclick = async function () {
        await fetch(`/client/remove-all-items`);
        window.location.reload();
        alert("Order was successfully placed.");
    }
    await populateTable();
}

const populateTable = async () => {
    const table = document.getElementById('product-cart');
    const res = await fetch('/get-product-cart-items');
    const requests = await res.json();

    const createRow =
        (cartId, nameText, priceText, quantityText) => {
            const row = document.createElement('tr');

            const name = document.createElement('td');
            const price = document.createElement('td');
            const quantity = document.createElement('td');
            const actions = document.createElement('td');

            name.innerText = nameText;
            price.innerText = (Number.parseInt(priceText) * Number.parseInt(quantityText)).toString();
            quantity.innerText = quantityText;

            let removeFromCart = document.createElement("button");
            removeFromCart.innerText = "remove"
            removeFromCart.className = "badge badge-danger";
            removeFromCart.style.marginRight = "10px";
            removeFromCart.onclick = async function () {
                await fetch(`/remove-from-cart/${cartId}`);
                window.location.reload();
            }

            actions.appendChild(removeFromCart);

            row.appendChild(name);
            row.appendChild(quantity);
            row.appendChild(price);
            row.appendChild(actions);

            return row;
        };

    let fullPrice = 0;

    for (let request of requests) {
        fullPrice += (Number.parseInt(request.product.price) * Number.parseInt(request.quantity))
        const row = createRow(request.id, request.product.name, request.product.price, request.quantity);
        table.appendChild(row);
    }

    document.getElementById('total-price').innerText = "Total price = " + fullPrice.toString();
}