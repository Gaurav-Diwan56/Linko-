// File Explorer and Editor State
let fileSystem = {};
let currentFile = null;

// Create a new file
function createFile() {
    const fileName = prompt("Enter the file name (e.g., index.html):");
    if (fileName) {
        if (fileSystem[fileName]) {
            alert("File already exists!");
        } else {
            fileSystem[fileName] = ""; // Empty file content
            updateFileExplorer();
            alert(`${fileName} created successfully!`);
        }
    }
}

// Update the File Explorer
function updateFileExplorer() {
    const fileList = document.getElementById("file-list");
    fileList.innerHTML = "";
    Object.keys(fileSystem).forEach(fileName => {
        const listItem = document.createElement("li");
        listItem.textContent = fileName;
        listItem.style.cursor = "pointer";
        listItem.addEventListener("click", () => openFile(fileName));
        fileList.appendChild(listItem);
    });
}

// Open a file in the editor
function openFile(fileName) {
    currentFile = fileName;
    document.getElementById("code-editor").value = fileSystem[fileName];
    alert(`Opened ${fileName}`);
}

// Save the current file
function saveFile() {
    if (currentFile) {
        const content = document.getElementById("code-editor").value;
        fileSystem[currentFile] = content;
        alert(`${currentFile} saved successfully!`);
    } else {
        alert("No file is currently open!");
    }
}

// Run the code (basic HTML output)
function runCode() {
    if (currentFile && currentFile.endsWith(".html")) {
        const content = fileSystem[currentFile];
        const newWindow = window.open();
        newWindow.document.write(content);
        newWindow.document.close();
    } else {
        alert("Only HTML files can be run in the browser!");
    }
}

// Clear the editor
function clearEditor() {
    document.getElementById("code-editor").value = "";
    alert("Editor cleared!");
}

// Download all files as a .zip
function downloadProject() {
    const zip = new JSZip();
    Object.keys(fileSystem).forEach(fileName => {
        zip.file(fileName, fileSystem[fileName]);
    });
    zip.generateAsync({ type: "blob" }).then(content => {
        const link = document.createElement("a");
        link.href = URL.createObjectURL(content);
        link.download = "project.zip";
        link.click();
    });
}
