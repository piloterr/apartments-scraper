<p align="center">
  <a href="https://www.piloterr.com"><img src="images/banner.png" alt="Apartments Scraper API by Piloterr – scrape Apartments data as JSON" width="100%"></a>
</p>

# Apartments Scraper API

[![Discord](https://img.shields.io/badge/Discord-Join%20the%20community-5865F2?logo=discord&logoColor=white)](https://discord.gg/CFj3ph8aHU) [![Get API key](https://img.shields.io/badge/Piloterr-Get%20a%20free%20API%20key-111827)](https://app.piloterr.com) [![API docs](https://img.shields.io/badge/Docs-OpenAPI-0EA5E9)](https://app.piloterr.com/openapi) [![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

The **Piloterr Apartments Scraper API** lets you extract public data from Apartments as **clean, structured JSON** — no proxies, headless browsers or HTML parsing to maintain. Endpoints available in production:

- **Apartments Property API** — Extract a full Apartments.com rental listing from a property URL, including amenities, gallery, description, geolocation, and unit details via browser rendering.
- **Apartments Search API** — Search Apartments.com rentals from a full city or browse URL and retrieve prices, photos, addresses, phone numbers, and pagination via browser rendering.

Use it for property price monitoring, market analytics, investment sourcing or rental yield models.

| Endpoint | Path | Credits | What you get |
|---|---|---|---|
| [Apartments Property API](#apartments-property-api) | `/v2/apartments/property` | 2 | Extract a full Apartments.com rental listing from a property URL, including amenities, gallery, description, geolocation, and unit details via browser rendering. |
| [Apartments Search API](#apartments-search-api) | `/v2/apartments/search` | 2 | Search Apartments.com rentals from a full city or browse URL and retrieve prices, photos, addresses, phone numbers, and pagination via browser rendering. |

## Table of contents

- [How it works](#how-it-works)
- [Apartments Property API](#apartments-property-api)
- [Apartments Search API](#apartments-search-api)
- [Code examples in other languages](#code-examples-in-other-languages)
- [Practical use cases](#practical-use-cases)
- [Why Piloterr?](#why-piloterr)
- [FAQ](#faq)
- [Official SDKs](#official-sdks)
- [Related scrapers](#related-scrapers)
- [Support](#support)

## How it works

1. **Create a free Piloterr account** and copy your API key from the [dashboard](https://app.piloterr.com).
2. **Send a `GET` (or `POST`) request** to `https://api.piloterr.com/v2/...` with the header `x-api-key: YOUR_API_KEY`.
3. **Receive structured JSON** — Piloterr handles rendering, proxies, retries and parsing.

```bash
export PILOTERR_API_KEY="your-api-key"
pip install requests        # Python
# TypeScript: Node.js 18+ (native fetch), run with `npx tsx file.ts`
```

> Every endpoint accepts both `GET` (query string) and `POST` (JSON body with the same fields).

## Apartments Property API

Extract a full Apartments.com rental listing from a property URL, including amenities, gallery, description, geolocation, and unit details via browser rendering.

`GET | POST https://api.piloterr.com/v2/apartments/property` · **2 credits per request** · [Full reference](https://app.piloterr.com/openapi)

### Request parameters

| Parameter | Type | Required | Description |
|---|---|---|---|
| `query` | string | ✅ | Full Apartments.com property listing URL. |

### Python example

```python
import json
import os
import requests

response = requests.get(
    "https://api.piloterr.com/v2/apartments/property",
    headers={"x-api-key": os.environ["PILOTERR_API_KEY"]},
    params={
        "query": "https://www.apartments.com/141-e-33rd-st-new-york-ny-unit-si-fl16-id43/p6b4mes/",
    },
    timeout=120,
)
response.raise_for_status()
data = response.json()

print(json.dumps(data, indent=2, ensure_ascii=False)[:2000])
```

### TypeScript example

```typescript
type ApartmentsProperty = {
  id?: string;
  url?: string;
  image?: string;
  phone?: string;
  title?: string;
  units?: unknown[];
  images?: string[];
  address?: string;
  bedrooms?: string;
  amenities?: string[];
  bathrooms?: string;
  rent_range?: unknown;
  description?: string;
  geolocation?: {
    latitude?: number;
    longitude?: number;
  };
  square_feet?: string;
};

const params = new URLSearchParams({
  "query": "https://www.apartments.com/141-e-33rd-st-new-york-ny-unit-si-fl16-id43/p6b4mes/",
});

const response = await fetch(`https://api.piloterr.com/v2/apartments/property?${params}`, {
  headers: { "x-api-key": process.env.PILOTERR_API_KEY! },
});
if (!response.ok) throw new Error(`Piloterr error ${response.status}: ${await response.text()}`);

const data = (await response.json()) as ApartmentsProperty;

console.log(JSON.stringify(data, null, 2).slice(0, 2000));
```

<details>
<summary><b>POST variant</b></summary>

```http
POST https://api.piloterr.com/v2/apartments/property
x-api-key: YOUR_API_KEY
Content-Type: application/json

{"query": "https://www.apartments.com/141-e-33rd-st-new-york-ny-unit-si-fl16-id43/p6b4mes/"}
```
</details>

### Output example

Example response from the API reference, abridged — real values depend on the page you scrape.

```json
{
  "id": "p6b4mes",
  "url": "https://www.apartments.com/141-e-33rd-st-new-york-ny-unit-si-fl16-id43/p6b4mes/",
  "image": "https://images1.apartments.com/i2/SF9HSqkGqXN0cjmR2cUNbgLuxyXklZLpKYtInYim0XI/111/141-e-33rd-st-unit-si-fl16-id43-new-york-ny-primary-pho…",
  "phone": "example",
  "title": "$7,210",
  "units": [],
  "images": [
    "https://images1.apartments.com/i2/SF9HSqkGqXN0cjmR2cUNbgLuxyXklZLpKYtInYim0XI/111/141-e-33rd-st-unit-si-fl16-id43-new-york-ny-primary-pho…",
    "https://images1.apartments.com/i2/_LYYrJa6ncHlDFoi81j_UsjuW84Nvn4w9YGihAPQA3o/117/141-e-33rd-st-unit-si-fl16-id43-new-york-ny-building-ph…"
  ],
  "address": "New York, NY 10016",
  "bedrooms": "2",
  "amenities": [
    "Wi-Fi",
    "Air Conditioning"
  ],
  "bathrooms": "1.5",
  "rent_range": null,
  "description": "141 E 33rd St Unit SI FL16-ID43\n\n1 - 12 Month Lease , Available Aug 9\n\nFeel at home wherever you choose to live with Blueground. You’ll l…",
  "geolocation": {
    "latitude": 40.74576,
    "longitude": -73.97978
  },
  "square_feet": "850 sq ft"
}
```

### Response fields

| Field | Type | Description |
|---|---|---|
| `id` | string |  |
| `url` | string |  |
| `image` | string |  |
| `phone` | string |  |
| `title` | string |  |
| `units` | any[] |  |
| `images` | string[] |  |
| `address` | string |  |
| `bedrooms` | string |  |
| `amenities` | string[] |  |
| `bathrooms` | string |  |
| `rent_range` | object |  |
| `description` | string |  |
| `geolocation` | object |  |
| `geolocation.latitude` | number |  |
| `geolocation.longitude` | number |  |
| `square_feet` | string |  |

## Apartments Search API

Search Apartments.com rentals from a full city or browse URL and retrieve prices, photos, addresses, phone numbers, and pagination via browser rendering.

`GET | POST https://api.piloterr.com/v2/apartments/search` · **2 credits per request** · [Full reference](https://app.piloterr.com/openapi)

### Request parameters

| Parameter | Type | Required | Description |
|---|---|---|---|
| `query` | string | ✅ | Full Apartments.com search or city browse URL. |

### Python example

```python
import json
import os
import requests

response = requests.get(
    "https://api.piloterr.com/v2/apartments/search",
    headers={"x-api-key": os.environ["PILOTERR_API_KEY"]},
    params={
        "query": "https://www.apartments.com/new-york-ny/3",
    },
    timeout=120,
)
response.raise_for_status()
data = response.json()

print(json.dumps(data, indent=2, ensure_ascii=False)[:2000])
```

### TypeScript example

```typescript
type ApartmentsSearch = {
  results?: {
    id?: string;
    url?: string;
    image?: string;
    phone?: string;
    title?: string;
    address?: string;
    pricing?: {
      beds?: string;
      price?: string;
    }[];
    has_video?: boolean;
    min_price?: number;
    image_count?: number;
    country_code?: string;
    property_type?: string;
    street_address?: string;
    has_virtual_tour?: boolean;
  }[];
  pagination?: {
    current?: number;
    next_url?: string;
    total_count?: number;
    has_next_page?: boolean;
  };
};

const params = new URLSearchParams({
  "query": "https://www.apartments.com/new-york-ny/3",
});

const response = await fetch(`https://api.piloterr.com/v2/apartments/search?${params}`, {
  headers: { "x-api-key": process.env.PILOTERR_API_KEY! },
});
if (!response.ok) throw new Error(`Piloterr error ${response.status}: ${await response.text()}`);

const data = (await response.json()) as ApartmentsSearch;

console.log(JSON.stringify(data, null, 2).slice(0, 2000));
```

<details>
<summary><b>POST variant</b></summary>

```http
POST https://api.piloterr.com/v2/apartments/search
x-api-key: YOUR_API_KEY
Content-Type: application/json

{"query": "https://www.apartments.com/new-york-ny/3"}
```
</details>

### Output example

Example response from the API reference, abridged — real values depend on the page you scrape.

```json
{
  "results": [
    {
      "id": "p6b4mes",
      "url": "https://www.apartments.com/141-e-33rd-st-new-york-ny-unit-si-fl16-id43/p6b4mes/",
      "image": "https://images1.apartments.com/i2/SF9HSqkGqXN0cjmR2cUNbgLuxyXklZLpKYtInYim0XI/118/141-e-33rd-st-unit-si-fl16-id43-new-york-ny-building-ph…",
      "phone": "example",
      "title": "141 E 33rd St Unit SI FL16-ID43",
      "address": "141 E 33rd St, New York, NY 10016",
      "pricing": [
        {}
      ],
      "has_video": false,
      "min_price": 7210,
      "image_count": 23,
      "country_code": "US",
      "property_type": "Apartment for Rent",
      "street_address": "141 E 33rd St",
      "has_virtual_tour": true
    },
    {
      "id": "kkt0hfb",
      "url": "https://www.apartments.com/53-park-pl-new-york-ny-unit-si-fl3-id53/kkt0hfb/",
      "image": "https://images1.apartments.com/i2/6wscRtCcX-3nE67ze4o41rAW9_6LCo0nJX22IYicrAk/118/53-park-pl-unit-si-fl3-id53-new-york-ny-building-photo.…",
      "phone": "example",
      "title": "53 Park Pl Unit SI FL3-ID53",
      "address": "53 Park Pl, New York, NY 10007",
      "pricing": [
        {}
      ],
      "has_video": false,
      "min_price": 5110,
      "image_count": 28,
      "country_code": "US",
      "property_type": "Apartment for Rent",
      "street_address": "53 Park Pl",
      "has_virtual_tour": true
    }
  ],
  "pagination": {
    "current": 3,
    "next_url": "https://www.apartments.com/new-york-ny/4/",
    "total_count": 7775,
    "has_next_page": true
  }
}
```

### Response fields

| Field | Type | Description |
|---|---|---|
| `results[]` | object[] |  |
| `results[].id` | string |  |
| `results[].url` | string |  |
| `results[].image` | string |  |
| `results[].phone` | string |  |
| `results[].title` | string |  |
| `results[].address` | string |  |
| `results[].pricing[]` | object[] |  |
| `results[].has_video` | boolean |  |
| `results[].min_price` | integer |  |
| `results[].image_count` | integer |  |
| `results[].country_code` | string |  |
| `results[].property_type` | string |  |
| `results[].street_address` | string |  |
| `results[].has_virtual_tour` | boolean |  |
| `pagination` | object |  |
| `pagination.current` | integer |  |
| `pagination.next_url` | string |  |
| `pagination.total_count` | integer |  |
| `pagination.has_next_page` | boolean |  |

## Code examples in other languages

Ready-to-run examples live in the [`examples/`](examples) folder. Each one reads your key from the `PILOTERR_API_KEY` environment variable.

| Language | Property | Search |
|---|---|---|
| cURL (Bash) | [`property`](examples/curl/property.sh) | [`search`](examples/curl/search.sh) |
| PHP | [`property`](examples/php/property.php) | [`search`](examples/php/search.php) |
| C# | [`property`](examples/csharp/Property.cs) | [`search`](examples/csharp/Search.cs) |
| Go | [`property`](examples/go/property.go) | [`search`](examples/go/search.go) |
| Java | [`property`](examples/java/PropertyExample.java) | [`search`](examples/java/SearchExample.java) |
| Ruby | [`property`](examples/ruby/property.rb) | [`search`](examples/ruby/search.rb) |
| Node.js | [`property`](examples/nodejs/property.mjs) | [`search`](examples/nodejs/search.mjs) |

## Practical use cases

- **Price monitoring** — Track asking prices and price drops per area.
- **Market analytics** — Measure inventory, days on market and price per m² / sqft.
- **Investment sourcing** — Detect undervalued properties as soon as they are listed.
- **Rental yield models** — Combine sale and rent listings to estimate yields.
- **Proptech products** — Power portals, valuation tools and alerts with structured listings.

## Why Piloterr?

- **No infrastructure** — no proxies, headless browsers or CAPTCHA solving to run yourself.
- **Structured JSON** — stable, documented fields instead of brittle HTML selectors.
- **Pay per request** — transparent credit cost per endpoint, failed requests are retried for you.
- **One API key, hundreds of sources** — Apartments plus many other e-commerce, search and company data endpoints in the [Piloterr library](https://www.piloterr.com/library).
- **Any language** — plain HTTPS + JSON, with examples for Python, TypeScript, cURL, PHP, C#, Go, Java, Ruby and Node.js.

## FAQ

### Is it legal to scrape Apartments?

Collecting publicly available data is generally allowed in many jurisdictions, but rules depend on your country, the data you collect and how you use it. Avoid personal data unless you have a lawful basis, respect the website's terms where they apply, and get legal advice for your specific use case. This is not legal advice.

### Do I need proxies or a headless browser?

No. Piloterr handles rendering, proxy rotation and retries on its side. You only send an HTTPS request with your `x-api-key` header and receive JSON.

### How much does a request cost?

Credits per call: `/v2/apartments/property` **2**, `/v2/apartments/search` **2**. See the [Piloterr website](https://www.piloterr.com) for plans.

### What data does the Apartments API return?

Each endpoint returns structured JSON: **Apartments Property** — `id`, `url`, `image`, `phone`, `title`, `units`; **Apartments Search** — `results[]`, `results[].id`, `results[].url`, `results[].image`, `results[].phone`, `results[].title`.

### Which languages are supported?

Any language that can make an HTTP request. This README shows Python and TypeScript; the [`examples/`](examples) folder contains cURL, PHP, C#, Go, Java, Ruby and Node.js.

## Official SDKs

Use Piloterr from your own language with a typed client:

| SDK | Package | Install |
| --- | --- | --- |
| Node.js | [`@piloterr/sdk`](https://www.npmjs.com/package/@piloterr/sdk) | `npm install @piloterr/sdk` |
| Python | [`piloterr`](https://pypi.org/project/piloterr/) | `pip install piloterr` |
| Go | [`piloterr-go`](https://pkg.go.dev/github.com/piloterr/piloterr-go) | `go get github.com/piloterr/piloterr-go` |
| .NET | [`Piloterr`](https://www.nuget.org/packages/Piloterr) | `dotnet add package Piloterr` |
| Ruby | [`piloterr`](https://rubygems.org/gems/piloterr) | `gem install piloterr` |
| PHP | [`piloterr/sdk`](https://packagist.org/packages/piloterr/sdk) | `composer require piloterr/sdk` |
| Java | [`piloterr-java`](https://github.com/piloterr/piloterr-java) | build from source, see its README |
| CLI | [`@piloterr/cli`](https://www.npmjs.com/package/@piloterr/cli) | `npm install -g @piloterr/cli` |
| MCP server | [`@piloterr/mcp`](https://www.npmjs.com/package/@piloterr/mcp) | `npx -y @piloterr/mcp` |

## Related scrapers

- [Argenprop Scraper API](https://github.com/piloterr/argenprop-scraper)
- [Centris Scraper API](https://github.com/piloterr/centris-scraper)
- [Fotocasa Scraper API](https://github.com/piloterr/fotocasa-scraper)
- [Funda Scraper API](https://github.com/piloterr/funda-scraper)
- [Homestra Scraper API](https://github.com/piloterr/homestra-scraper)
- [Idealista Scraper API](https://github.com/piloterr/idealista-scraper)
- [All Piloterr scrapers on GitHub](https://github.com/piloterr)

## Support

- 💬 Join the [Piloterr Discord](https://discord.gg/CFj3ph8aHU) to ask questions and share what you build.
- 📚 Browse the [API reference (OpenAPI)](https://app.piloterr.com/openapi) and the [Piloterr library](https://www.piloterr.com/library).
- 🔑 [Get your API key](https://app.piloterr.com).

---

<p align="center">
  <a href="https://www.piloterr.com">
    <picture>
      <source media="(prefers-color-scheme: dark)" srcset="images/logo-dark.svg">
      <img src="images/logo.svg" alt="Piloterr" height="32">
    </picture>
  </a>
  <br>
  <sub>Built by <a href="https://www.piloterr.com">Piloterr</a> — web scraping APIs for e-commerce, search, real estate and more, one API key.</sub>
</p>

<sub>Apartments is a trademark of its respective owner. This repository is not affiliated with or endorsed by Apartments. Examples are released under the MIT license.</sub>
