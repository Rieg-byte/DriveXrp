package com.rieg.drivexrp.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rieg.drivexrp.R
import com.rieg.drivexrp.domain.model.Car
import com.rieg.drivexrp.ui.components.NoConnectionScreen
import com.rieg.drivexrp.ui.components.XrpButton
import com.rieg.drivexrp.ui.components.XrpOutlinedButton
import com.rieg.drivexrp.ui.theme.DriveXrpTheme


@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val homeUiState by homeViewModel.homeUiState.collectAsState()
    HomeScreen(
        homeUiState = homeUiState,
        query = homeViewModel.userInput,
        onQueryChange = homeViewModel::updateUserInput,
        getCarsByQuery = homeViewModel::getCarList,
        onRefresh = homeViewModel::repeat
    )
}

@Composable
private fun HomeScreen(
    homeUiState: HomeUiState = HomeUiState.Loading,
    query: String,
    onQueryChange: (String) -> Unit,
    getCarsByQuery: (String) -> Unit,
    onRefresh: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        SearchBar(
            query = query,
            onQueryChange = onQueryChange,
            getCarsByQuery = getCarsByQuery
        )
        when (homeUiState) {
            is HomeUiState.Success -> CarList(homeUiState.listOfCars)
            is HomeUiState.Loading -> SearchResultLoading()
            is HomeUiState.Error -> NoConnectionScreen(onRefresh = onRefresh)
            is HomeUiState.NotFound -> SearchResultNotFound(query = homeUiState.query)
        }
    }
}

@Composable
fun CarList(cars: List<Car>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(cars) { car ->
            CarCard(car)
        }
    }
}

@Composable
fun SearchResultNotFound(query: String) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(textAlign = TextAlign.Center, text = buildAnnotatedString {
            append(stringResource(id = R.string.on_query))
            append(' ')
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                append(query)
            }
            append(' ')
            append(stringResource(id = R.string.not_found))
        })
    }
}

@Composable
fun SearchResultLoading() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_car_loading),
                contentDescription = null
            )
            Text(
                text = stringResource(R.string.find_car_loading),
                style = MaterialTheme.typography.titleMedium
            )
            CircularProgressIndicator(modifier = Modifier.size(14.dp))
        }
    }
}


@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    getCarsByQuery: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = onQueryChange,
            singleLine = true,
            placeholder = {
                Text(stringResource(R.string.enter_car_brand))
            },
            trailingIcon = {
                IconButton(
                    onClick = { getCarsByQuery(query) }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = stringResource(R.string.search)
                    )
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
                getCarsByQuery(query)
            }),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color.LightGray.copy(alpha = 0.2f), shape = RoundedCornerShape(8.dp)),
        )
        Spacer(Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.find_car),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
        )
    }
}

@Composable
fun CarCard(car: Car) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = car.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = car.manufacturer,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                SpanStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append("${car.pricePerDay}₽ ")
                            }
                            withStyle(
                                SpanStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal)
                            ) {
                                append(stringResource(R.string.in_day))
                            }
                        },
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row {
                        Icon(
                            painter = painterResource(R.drawable.ic_gearbox),
                            contentDescription = null,
                            tint = Color.Gray
                        )
                        Text(
                            text = car.transmission,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Icon(
                            painter = painterResource(R.drawable.ic_fuel),
                            contentDescription = null,
                            tint = Color.Gray
                        )
                        Text(
                            text = car.fuelType,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                }

                Image(
                    painter = painterResource(R.drawable.iris_1),
                    contentDescription = null,
                    modifier = Modifier
                        .width(140.dp)
                        .height(120.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.FillBounds
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row {
                XrpButton(
                    onClick = {  },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = stringResource(R.string.book),
                        style = MaterialTheme.typography.labelSmall,
                        maxLines = 1
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                XrpOutlinedButton(
                    onClick = {  },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = stringResource(R.string.details),
                        style = MaterialTheme.typography.labelSmall,
                        maxLines = 1
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    DriveXrpTheme {
        HomeScreen(
            homeUiState = HomeUiState.Success(
                listOfCars = listOf(
                    Car(
                        name = "S 500 Sedan",
                        manufacturer = "Mercedes-Benz",
                        pricePerDay = 2500,
                        transmission = "А/Т",
                        fuelType = "Бензин",
                        imageUrl = ""
                    ),
                    Car(
                        name = "S 500 Sedan",
                        manufacturer = "Mercedes-Benz",
                        pricePerDay = 2500,
                        transmission = "А/Т",
                        fuelType = "Бензин",
                        imageUrl = ""
                    )
                )
            ),
            query = "",
            onQueryChange = {},
            getCarsByQuery = {},
            onRefresh = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchResultLoadingPreview() {
    DriveXrpTheme {
        SearchResultLoading()
    }
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    DriveXrpTheme {
        SearchBar(
            query = "",
            onQueryChange = {},
            getCarsByQuery = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CardCarPreview() {
    DriveXrpTheme {
        CarCard(
            Car(
                name = "S 500 Sedan",
                manufacturer = "Mercedes-Benz",
                pricePerDay = 2500,
                transmission = "А/Т",
                fuelType = "Бензин",
                imageUrl = ""
            )
        )
    }
}

