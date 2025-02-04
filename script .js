// User Database (Temporary Storage)
let users = {};
let books = [];

// Sign Up Function
function signup() {
    let id = document.getElementById("signup-id").value;
    let password = document.getElementById("signup-password").value;
    let type = document.getElementById("user-type").value;

    if (id && password) {
        if (!users[id]) {
            users[id] = { password, type };
            alert("Signup successful! Now login.");
        } else {
            alert("User ID already exists!");
        }
    } else {
        alert("Please fill all fields!");
    }
}

// Login Function
function login() {
    let id = document.getElementById("login-id").value;
    let password = document.getElementById("login-password").value;

    if (users[id] && users[id].password === password) {
        alert("Login successful!");
        document.getElementById("auth-section").style.display = "none";
        
        if (users[id].type === "publisher") {
            document.getElementById("publisher-form").style.display = "block";
        }
        
        document.getElementById("book-list").style.display = "block";
        displayBooks();
    } else {
        alert("Invalid login credentials!");
    }
}

// Function to Add Books (Only for Publishers)
function addBook() {
    let title = document.getElementById("book-title").value;
    let author = document.getElementById("book-author").value;
    let publisher = document.getElementById("book-publisher").value;

    if (title && author && publisher) {
        books.push({ title, author, publisher });
        alert("Book published successfully!");
        displayBooks();
    } else {
        alert("Please enter all book details!");
    }
}

// Function to Display Books
function displayBooks() {
    let bookList = document.getElementById("books");
    bookList.innerHTML = ""; // Clear previous list

    books.forEach((book, index) => {
        let bookItem = document.createElement("div");
        bookItem.className = "book-item";
        bookItem.innerHTML = `<h3>${book.title}</h3>
                              <p>Author: ${book.author}</p>
                              <p>Publisher: ${book.publisher}</p>`;
        bookList.appendChild(bookItem);
    });
}
