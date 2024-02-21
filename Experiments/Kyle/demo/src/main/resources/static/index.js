const button = document.getElementById("clicker");
const currentCount = document.getElementById("currentCount");

const updateCount = () => {
    currentCount.textContent++;
}

fetch("/count")
	.then((response) => response.json())
	.then((data) => {
		currentCount.textContent = data;
	})
	.catch((error) => {
		console.error("Failed to fetch count:", error);
	});

button.addEventListener("click", () => {
	fetch("/count+", {
		method: "POST",
	})
		.then(updateCount())
		.catch((error) => {
			console.log("Error updating count");
		});
});
