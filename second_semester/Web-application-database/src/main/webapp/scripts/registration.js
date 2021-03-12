const checkFormInputs =
    async () => {
        document.getElementById('registration-submit').disabled = true;

        const USER_TYPES = new Set(['client', 'administrator']);

        const data = new Map([
            ['username', document.getElementById('username').value],
            ['role', document.getElementById('role').value],
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

        if (data.get('username').length < 5) {
            formFailure(`User Name is too short. The minimum allowed length is 5`);
            return;
        }

        if (data.get('username').length > 20) {
            formFailure(`User Name is too long. The maximum allowed length is 20.`);
            return;
        }


        if (!USER_TYPES.has(data.get('role').toLowerCase())) {
            formFailure(`The selected role in the registration was not recognised. Please try again.`);
            return;
        }

        const encodedData = [];
        data.forEach((value, key) => {
            encodedData.push(`${key}=${value}`);
        });

    }

const formFailure = (errorMessage) => {
    const modalText = document.getElementById('failureModalText');
    modalText.innerText = errorMessage;
    $('#failureModal').modal(null);
    document.getElementById('registration-submit').disabled = false;
}