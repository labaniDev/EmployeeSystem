


function registerUser(){

    const employee ={
        "name":document.getElementById("name").value,
        "email":document.getElementById("email").value,
        "phone":document.getElementById("phone").value,
        "password":document.getElementById("password").value,
        "confirmPassword":document.getElementById("confirmPassword").value
    };
    // const name=document.getElementById("name").value;
    // const email=document.getElementById("email").value;
    // const phone=document.getElementById("phone").value;
    // const password=document.getElementById("password").value;
    // const confirmPassword=document.getElementById("confirmPassword").value;

    console.log(employee)

    callSpringBootPostAPI(employee)
    .then(response => {
        // Handle the response data
        console.log('Response from Spring Boot API:', response);

        document.getElementById('table-container').innerHTML = generateTable(response);
    })
    .catch(error => {
        // Handle any errors that occurred during the fetch operation
        console.error('Error calling Spring Boot POST API:', error);
    });
    
}

function callSpringBootPostAPI(data) {
    return new Promise((resolve, reject) => {
        // Make a fetch request to your Spring Boot API endpoint
        fetch('http://localhost:8080/employee/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                // You can add any additional headers your API requires
            },
            // Convert the data object to JSON format
            body: JSON.stringify(data),
        })
        .then(response => {
            // Check if the response is successful (status code between 200 and 299)
            if (response.ok) {
                // If successful, parse the JSON response
                return response.json();
            } else {
                // If the response is not successful, throw an error with the status text
                throw new Error('Error: ' + response.statusText);
            }
        })
        .then(data => {
            // Resolve the promise with the response data
            resolve(data);
        })
        .catch(error => {
            // If an error occurs during the fetch operation, reject the promise with the error message
            reject(error);
        });
    });
}


function generateTable(data) {
    let html = '<table border="1">';
    // Add table header
    html += '<tr>';
    for (let key in data[0]) {
        html += `<th>${key}</th>`;
    }
    html += '</tr>';

    // Add table rows
    data.forEach(item => {
        html += '<tr>';
        for (let key in item) {
            html += `<td>${item[key]}</td>`;
        }
        html += '</tr>';
    });

    html += '</table>';
    return html;
}


// // Example usage:
// const postData = {
//     key1: 'value1',
//     key2: 'value2'
// };


