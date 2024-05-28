async function performSearch() {
    const query = document.getElementById('searchBar').value;
    const response = await fetch(`https://vdasop.search.windows.net/indexes/documents-index/docs?api-version=2021-04-30-Preview&search=${query}`, {
        headers: {
            'api-key': 'RCbC3b8WjC2sGpdala7bbMgDqDn5U7nlOgJ46d4bUdAzSeBSvpR5'
        }
    });
    const data = await response.json();
    displayResults(data.value);
}

function displayResults(results) {
    const resultsDiv = document.getElementById('results');
    resultsDiv.innerHTML = '';
    results.forEach(result => {
        const resultItem = document.createElement('div');
        resultItem.innerHTML = `
            <h3>${result.fileName}</h3>
            <p>${result.fileLocation}</p>
            <p>${result.content.substring(0, 100)}...</p>
        `;
        resultItem.onclick = () => displayDocument(result);
        resultsDiv.appendChild(resultItem);
    });
}

function displayDocument(result) {
    const documentDisplay = document.getElementById('documentDisplay');
    documentDisplay.innerHTML = `
        <h2>${result.fileName}</h2>
        <p>${result.fileLocation}</p>
        <div>${result.content}</div>
    `;
}
