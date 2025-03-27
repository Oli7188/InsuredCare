document.addEventListener("DOMContentLoaded", function () {
    const loginLink = document.getElementById("loginLink");
    const logoutMenu = document.getElementById("logoutMenu");
    const logoutLink = document.getElementById("logoutLink");

    // Získanie stavu prihlásenia z localStorage
    let isLoggedIn = localStorage.getItem('isLoggedIn') === 'true';

    // Funkcia na aktualizáciu menu podľa stavu prihlásenia
    function updateMenu() {
        if (isLoggedIn) {
            loginLink.textContent = "Môj profil";
            logoutMenu.style.display = "block";
        } else {
            loginLink.textContent = "Prihlásiť sa";
            logoutMenu.style.display = "none";
        }
    }

    // Funkcia na spracovanie prihlásenia
    function handleLogin(event) {
        event.preventDefault(); // Zabránime odoslaniu formulára (stránka sa neobnoví)

        const email = prompt('Zadajte email:');
        const password = prompt('Zadajte heslo:');

        // Skontrolujeme, či zadané údaje sú správne
        if (email === 'admin@example.com' && password === 'admin123') {
            isLoggedIn = true;
            localStorage.setItem('isLoggedIn', 'true'); // Uloženie stavu prihlásenia
            updateMenu();
        } else {
            alert('Nesprávne prihlasovacie údaje!');
        }
    }

    // Kliknutie na prihlásenie
    loginLink.addEventListener("click", handleLogin);

    // Kliknutie na odhlásenie
    logoutLink.addEventListener("click", function (e) {
        e.preventDefault();
        isLoggedIn = false;
        localStorage.setItem('isLoggedIn', 'false'); // Uloženie stavu odhlásenia
        updateMenu();
    });


    updateMenu();

    // Funkcia na načítanie poistených zo servera
    function fetchPoisteny() {
        const poistenyListDiv = document.getElementById('poistenyList');
        const loadingMessage = document.getElementById('loadingMessage');

        loadingMessage.style.display = 'block'; // Zobrazenie načítavacej správy

        // Požiadavka na API
        fetch('/api/poisteny/', {
            method: 'GET', // Typ požiadavky GET
            headers: {
                'Content-Type': 'application/json',
            },
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Chyba pri načítaní poistených');
                }
                return response.json();  // Prevod odpovede na JSON
            })
            .then(data => {
                displayPoisteny(data);  // Zobrazenie poistených
                loadingMessage.style.display = 'none';  // Skrytie načítavacej správy
            })
            .catch(error => {
                console.error('Error fetching poisteny:', error);
                poistenyListDiv.innerHTML = '<p class="error-message">Došlo k chybe pri načítaní poistených. Skúste to neskôr.</p>';
                loadingMessage.style.display = 'none';  // Skrytie načítavacej správy pri chybe
            });
    }

    // Funkcia na zobrazenie poistených na stránke
    function displayPoisteny(poisteny) {
        const poistenyListDiv = document.getElementById('poistenyList');
        poistenyListDiv.innerHTML = '';  // Vyprázdnenie zoznamu pred pridaním nových položiek

        if (poisteny.length === 0) {
            poistenyListDiv.innerHTML = '<p>Žiadni poistení zatiaľ neexistujú.</p>';
        } else {
            poisteny.forEach(p => {
                const poistenyDiv = document.createElement('div');
                poistenyDiv.classList.add('poisteny-item');
                poistenyDiv.innerHTML =
                    `<h3>${p.meno} ${p.priezvisko}</h3><p>Adresa: ${p.adresa}</p>`;
                poistenyListDiv.appendChild(poistenyDiv);
            });
        }
    }


    fetchPoisteny();
});
