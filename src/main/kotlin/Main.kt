package cinema

var rows: UInt = 0u
var seats: UInt = 0u
var result: UInt = 0u
var bought = 0
var income: UInt = 0u
val cinema: MutableList<MutableList<Char>> = mutableListOf()


fun main() {
    layout()
    while(menu()) {}
}

fun menu(): Boolean {
    println(" ")
    println("1. Show the seats")
    println("2. Buy a ticket")
    println("3. Statistics")
    println("0. Exit")
    print("> ")
    val action = readln().toInt()
    var result = true
    when(action) {
        1 -> showTheSeats()

        2 -> buyATicket()

        3 -> statistics()

        0 -> result = false

        else -> println("error")
    }
    return result
}

fun layout() {
    var cont = true
    while(cont) {
        try {
            println("  ")
            println("Enter the number of rows:")
            print("> ")
            rows = readln().toUInt()
            println("Enter the number of seats in each row:")
            print("> ")
            seats = readln().toUInt()
            if (rows == 0u || seats == 0u) throw Exception("Wrong Input!")
        }
        catch (e: Exception) {
            println(e.message)
            continue
        }
        cont = false
    }
    cinemaCreator()
}

fun statistics() {
    println(" ")
    println("Number of purchased tickets: $bought")
    val percent = (100.0 * bought)/(seats.toDouble()*rows.toDouble())
    val formatPercent = "%.2f".format(percent)
    println("Percentage: $formatPercent%")
    println("Current income: $$income")
    println("Total income: $${if (seats*rows < 60u) rows * seats * 10u 
    else (seats*(rows/2u)*10u) + (seats*(rows - rows/2u)*8u)}")
}

fun cinemaCreator() {
    for (i in 0u until rows)
    {
        val row = MutableList(seats.toInt()){'S'}
        cinema.add(row)
    }
}

fun buyATicket() {
    var cont = true
    var rowNumber: UInt = 0u
    var seatNumber: UInt = 0u
    while(cont) {
        try {
            println("Enter a row number:")
            print("> ")
            rowNumber = readln().toUInt()
            if (rowNumber > rows) throw Exception("Wrong input!")
            println("Enter a seat number in that row:")
            print("> ")
            seatNumber = readln().toUInt()
            if (seatNumber > seats) throw Exception("Wrong input!")
            if (cinema[rowNumber.toInt() - 1][seatNumber.toInt() - 1] == 'B') throw Exception("That ticket has already been purchased!")
        }
        catch (e: Exception) {
            println(e.message)
            continue
        }
        cont = false
    }
    result = if (seats*rows < 60u) 10u else {
        val i = if (rows%2u == 0u) rows/2u else rows/2u
        if (rowNumber in 1u..i) 10u else 8u
    }

    println(" ")
    println("Ticket price: $$result")
    cinema[rowNumber.toInt() - 1][seatNumber.toInt() - 1] = 'B'
    ++bought
    income += result
}

fun showTheSeats() {
        println(" ")
        println("Cinema:")
        print("  ")
        val seatsLayout: MutableList<Int> = mutableListOf()
        for (num in 1..seats.toInt()) seatsLayout.add(num)
        println(seatsLayout.joinToString(" "))
        var rowsCounter = 1
        for (element in cinema) {
            print("$rowsCounter ")
            ++rowsCounter
            println(element.joinToString(" "))
        }
        println(" ")
}