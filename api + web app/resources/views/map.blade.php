<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.7.1/dist/leaflet.css"
          integrity="sha512-xodZBNTC5n17Xt2atTPuE1HxjVMSvLVW9ocqUKLsCC5CXdbqCmblAshOMAS6/keqq/sMZMZ19scR4PsZChSR7A=="
          crossorigin=""/>
    <script src="https://unpkg.com/leaflet@1.7.1/dist/leaflet.js"
            integrity="sha512-XQoYMqMTK8LvdxXYG3nZ448hOEQiglfqkJs1NOQV44cWnUrBc8PkAOcXy20w0vlaXaVUearIOBhiXZ5V3ynxwA=="
            crossorigin=""></script>
    <link rel="stylesheet" href="{{ URL::asset('css/map.css') }}">
</head>
<body>
    @include("layout.header")
<main>
    <div class="my-card">
        <div class="weather loading">
            <a href="/"></a>
            <div class="city">Weather in Denver</div>
            <div class="temp">51°C</div>
            <div class="flex">
                <img src="https://openweathermap.org/img/wn/04n@2x.png" alt="" class="icon">
                <div class="description">Cloudy</div>
            </div>
            <div class="humidity">Humidity: 60%</div>
            <div class="wind">Wind speed: 6.2 km/h</div>
        </div>
    </div>
    <div class="wrapper">
        <div class="search">
            <input type="text" class="search-input">
            <button>search</button>
        </div>
        <div class="menu-layer ">
            <h3 class="menu__header">Activities:</h3>
            <div class="activities">
                @foreach($activities as $activity)
                    <div class="activity-item">
                        <h4>{{ $activity->id . ". " . $activity->lat }}</h4>
                        <span>{{ $activity->created_at->diffForHumans() }}</span>
                        <p>{{ $activity->lon }}</p>
                    </div>
                @endforeach
            </div>
        </div>
        <div class="map" id="map"></div>
    </div>
</main>

<script src="{{ URL::asset('js/map.js') }}"></script>
</body>
</html>
