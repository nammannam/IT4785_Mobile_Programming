import kotlin.math.abs

/**
 * Lớp Phân Số - đại diện cho một phân số dạng tử/mẫu
 * @param tu Tử số (mặc định = 1)
 * @param mau Mẫu số (mặc định = 1, phải khác 0)
 */
class PhanSo(private var tu: Int = 1, private var mau: Int = 1) : Comparable<PhanSo> {

    /**
     * Chuẩn hoá dấu: đưa dấu âm về tử, mẫu luôn dương
     * Input: Không có (sử dụng thuộc tính tu, mau)
     * Output: Không trả về (thay đổi trực tiếp tu, mau)
     * Ví dụ: 3/-5 -> -3/5, -3/-5 -> 3/5
     */
    private fun chuanHoaDau() {
        if (mau < 0) {
            tu = -tu
            mau = -mau
        }
    }

    /**
     * Phương thức nhập phân số từ bàn phím
     * Input: Nhập từ console
     *   - Tử số (Int, khác 0)
     *   - Mẫu số (Int, khác 0)
     * Output: Không trả về (cập nhật thuộc tính tu, mau)
     * Lặp lại nếu người dùng nhập tử hoặc mẫu = 0
     */
    fun input() {
        while (true) {
            print("Nhập tử số (khác 0): ")
            val tuNhap = readln().toInt()
            print("Nhập mẫu số (khác 0): ")
            val mauNhap = readln().toInt()

            if (tuNhap == 0 || mauNhap == 0) {
                println("Tử số và mẫu số đều phải khác 0. Vui lòng nhập lại!")
                continue
            }

            this.tu = tuNhap
            this.mau = mauNhap
            chuanHoaDau()
            break
        }
    }

    /**
     * Phương thức in phân số ra màn hình (dạng tu/mau)
     * Input: Không có (sử dụng thuộc tính tu, mau)
     * Output: In ra console dạng "tu/mau" (không xuống dòng)
     * Ví dụ: 3/5, -2/7
     */
    fun printPs() {
        print("$tu/$mau")
    }

    /**
     * Phương thức tối giản phân số
     * Input: Không có (sử dụng thuộc tính tu, mau)
     * Output: Không trả về (thay đổi trực tiếp tu, mau)
     * Ví dụ: 6/8 -> 3/4, 12/18 -> 2/3
     */
    fun toiGian() {
        val g = gcd(abs(tu), abs(mau))
        tu /= g
        mau /= g
        chuanHoaDau()
    }

    /**
     * So sánh phân số này với phân số khác
     * Input: other (PhanSo) - phân số cần so sánh
     * Output: Int
     *   - Trả về -1 nếu this < other
     *   - Trả về 0 nếu this == other
     *   - Trả về 1 nếu this > other
     * Dùng Long để tránh tràn số khi nhân chéo
     * Ví dụ: 1/2 < 3/4 -> trả về -1
     */
    override fun compareTo(other: PhanSo): Int {
        val left = tu.toLong() * other.mau.toLong()
        val right = other.tu.toLong() * mau.toLong()
        return when {
            left < right -> -1
            left > right -> 1
            else -> 0
        }
    }

    /**
     * Tính tổng với một phân số khác
     * Input: other (PhanSo) - phân số cần cộng
     * Output: PhanSo mới (đã được tối giản)
     * Công thức: a/b + c/d = (a*d + c*b) / (b*d)
     * Ví dụ: 1/2 + 1/3 = 5/6
     */
    fun add(other: PhanSo): PhanSo {
        val tuMoi = tu.toLong() * other.mau + other.tu.toLong() * mau
        val mauMoi = mau.toLong() * other.mau
        val kq = PhanSo(tuMoi.toInt(), mauMoi.toInt())
        kq.toiGian()
        return kq
    }

    /**
     * Chuyển phân số thành giá trị thực (số thực)
     * Input: Không có (sử dụng thuộc tính tu, mau)
     * Output: Double - giá trị thập phân của phân số
     * Ví dụ: 1/2 -> 0.5, 3/4 -> 0.75
     */
    fun toDoubleValue(): Double = tu.toDouble() / mau.toDouble()

    /**
     * Tính ƯỚC CHUNG LỚN NHẤT (UCLN) bằng thuật toán Euclid
     * Input:
     *   - a (Int) - số nguyên thứ nhất
     *   - b (Int) - số nguyên thứ hai
     * Output: Int - UCLN của a và b (luôn dương)
     * Ví dụ: gcd(12, 8) = 4, gcd(15, 25) = 5
     */
    private fun gcd(a: Int, b: Int): Int {
        var x = a
        var y = b
        if (x == 0 && y == 0) return 1
        while (y != 0) {
            val r = x % y
            x = y
            y = r
        }
        return abs(x)
    }

    /**
     * Chuyển phân số thành chuỗi
     * Input: Không có
     * Output: String dạng "tu/mau"
     */
    override fun toString(): String = "$tu/$mau"
}

/**
 * Hàm tiện ích: in mảng phân số trên một dòng
 * Input: arr (Array<PhanSo>) - mảng phân số cần in
 * Output: In ra console dạng "a/b, c/d, e/f, ..." và xuống dòng
 * Ví dụ: [1/2, 3/4, 5/6] -> "1/2, 3/4, 5/6"
 */
fun printArr(arr: Array<PhanSo>) {
    for (i in arr.indices) {
        arr[i].printPs()
        if (i != arr.lastIndex) print(", ")
    }
    println()
}

// ============== MAIN ==============

/**
 * Nhập số lượng phân số
 * Input: Nhập từ console - số nguyên n (số lượng phân số)
 * Output: Biến n kiểu Int
 */
print("Nhập số lượng phân số n: ")
val n = readln().toInt()

/**
 * Nhập mảng n phân số từ bàn phím
 * Input: Với mỗi phân số, nhập tử và mẫu (đều khác 0)
 * Output: Mảng arr chứa n phân số
 */
val arr = Array(n) { PhanSo() }
println("Nhập các phân số (tử và mẫu đều khác 0):")
for (i in 0 until n) {
    println("Phân số thứ ${i + 1}:")
    val ps = PhanSo()
    ps.input()
    arr[i] = ps
}

/**
 * In ra mảng phân số vừa nhập
 * Input: Mảng arr
 * Output: In ra console dạng "a/b, c/d, ..."
 */
println("\nMảng phân số vừa nhập:")
printArr(arr)

/**
 * Tối giản các phần tử của mảng
 * Input: Mảng arr (các phân số chưa tối giản)
 * Output: Mảng arr (các phân số đã tối giản)
 * In kết quả ra console
 */
for (ps in arr) ps.toiGian()
println("\nMảng sau khi tối giản:")
printArr(arr)

/**
 * Tính tổng các phân số
 * Input: Mảng arr
 * Output: Biến sum (PhanSo) - tổng đã tối giản
 * In kết quả ra console dạng phân số và số thập phân
 * Ví dụ: "5/6 = 0.8333..."
 */
var sum = PhanSo(0, 1) // Khởi tạo = 0/1 (tổng ban đầu = 0)
for (ps in arr) {
    sum = sum.add(ps)
}
print("\nTổng các phân số (đã tối giản): ")
sum.printPs()
println(" = ${sum.toDoubleValue()}")

/**
 * Tìm phân số có giá trị lớn nhất
 * Input: Mảng arr
 * Output: Biến maxPs (PhanSo?) - phân số lớn nhất (hoặc null nếu mảng rỗng)
 * In kết quả ra console
 */
val maxPs = arr.maxOrNull()
print("\nPhân số lớn nhất: ")
maxPs?.printPs()
println(maxPs?.let { " = ${it.toDoubleValue()}" } ?: "")

/**
 * Sắp xếp mảng theo thứ tự giảm dần
 * Input: Mảng arr
 * Output: Mảng sortedDesc - mảng mới đã sắp xếp giảm dần
 * In kết quả ra console
 * Ví dụ: [1/2, 3/4, 1/3] -> [3/4, 1/2, 1/3]
 */
val sortedDesc = arr.sortedArrayDescending()
println("\nMảng sau khi sắp xếp giảm dần:")
printArr(sortedDesc)