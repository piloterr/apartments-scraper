// Apartments Property API – C# example (.NET 6+, top-level statements)
// Usage: dotnet new console -n Piloterr && copy this file to Program.cs
//        PILOTERR_API_KEY=your-key dotnet run
using System.Text.Json;

var apiKey = Environment.GetEnvironmentVariable("PILOTERR_API_KEY")
    ?? throw new InvalidOperationException("Set PILOTERR_API_KEY");

using var http = new HttpClient { Timeout = TimeSpan.FromSeconds(120) };
http.DefaultRequestHeaders.Add("x-api-key", apiKey);

var query =
    "query=" + Uri.EscapeDataString("https://www.apartments.com/141-e-33rd-st-new-york-ny-unit-si-fl16-id43/p6b4mes/");

var response = await http.GetAsync("https://api.piloterr.com/v2/apartments/property?" + query);
var body = await response.Content.ReadAsStringAsync();
if (!response.IsSuccessStatusCode)
{
    Console.Error.WriteLine($"Piloterr error {(int)response.StatusCode}: {body}");
    return;
}

using var doc = JsonDocument.Parse(body);
Console.WriteLine(JsonSerializer.Serialize(doc, new JsonSerializerOptions { WriteIndented = true }));
