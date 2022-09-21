/**
The doubly linked list structure and manipulation was inspired by and borrowed
from https://golangbyexample.com/doubly-linked-list-golang/
We implemented the add, subtract and main functions.
*/

package main

import (
	"fmt"
)

type node struct {
	data int
	prev *node
	next *node
}

type doublyLinkedList struct {
	len  int
	tail *node
	head *node
}

func initDoublyList() *doublyLinkedList {
	return &doublyLinkedList{}
}

func (d *doublyLinkedList) AddFrontNodeDLL(data int) {
	newNode := &node{
		data: data,
	}
	if d.head == nil {
		d.head = newNode
		d.tail = newNode
	} else {
		newNode.next = d.head
		d.head.prev = newNode
		d.head = newNode
	}
	d.len++
	return
}

func (d *doublyLinkedList) AddEndNodeDLL(data int) {
	newNode := &node{
		data: data,
	}
	if d.head == nil {
		d.head = newNode
		d.tail = newNode
	} else {
		currentNode := d.head
		for currentNode.next != nil {
			currentNode = currentNode.next
		}
		newNode.prev = currentNode
		currentNode.next = newNode
		d.tail = newNode
	}
	d.len++
	return
}

func (d *doublyLinkedList) TraverseForward() error {
	if d.head == nil {
		return fmt.Errorf("TraverseError: List is empty")
	}
	temp := d.head
	for temp != nil {
		fmt.Printf("%v ", temp.data)
		temp = temp.next
	}
	fmt.Println()
	return nil
}

func (d *doublyLinkedList) TraverseReverse() error {
	if d.head == nil {
		return fmt.Errorf("TraverseError: List is empty")
	}
	temp := d.tail
	for temp != nil {
		fmt.Printf("%v ", temp.data)
		temp = temp.prev
	}
	fmt.Println()
	return nil
}

func (d *doublyLinkedList) Size() int {
	return d.len
}

/**
Adds two doubly linked lists together by traversing backwards through the nodes.
*/
func add(d1 *doublyLinkedList, d2 *doublyLinkedList, d3 *doublyLinkedList) error {
	if (d1.head == nil) || (d2.head == nil) {
		return fmt.Errorf("error: One or more list is empty")
	} else {
		carry := 0
		temp := d1.tail
		temp2 := d2.tail
		for (temp != nil) || (temp2 != nil) {
			result := 0
			if temp == nil {
				result = 0 + temp2.data + carry
				temp2 = temp2.prev
			} else if temp2 == nil {
				result = temp.data + 0 + carry
				temp = temp.prev
			} else {
				result = temp.data + temp2.data + carry
				temp = temp.prev
				temp2 = temp2.prev
			}
			carry = 0
			if result > 9 {
				result -= 10
				carry = 1
			}
			d3.AddFrontNodeDLL(result)
		}
		if (temp == nil) && (temp2 == nil) && carry > 0 {
			d3.AddFrontNodeDLL(carry)
		}
	}
	return nil
}

/**
Subtracts two doubly linked lists together by traversing backwards through the nodes.
*/
func subtract(d1 *doublyLinkedList, d2 *doublyLinkedList, d3 *doublyLinkedList) error {

	tempDoublyList := initDoublyList()

	if (d1.head == nil) || (d2.head == nil) {
		return fmt.Errorf("error: One or more list is empty")
	} else {
		carry := 0
		result := 0

		temp := d1.tail
		temp2 := d2.tail

		// While there is still elements in the lists:
		for (temp != nil) || (temp2 != nil) {

			// If list 1 has reached its end:
			if temp == nil {
				result = 0 - temp2.data - carry
				temp2 = temp2.prev

				// If list 2 has reached its end:
			} else if temp2 == nil {
				result = temp.data - carry
				temp = temp.prev

				// If both lists has elements
			} else {
				result = temp.data - temp2.data - carry
				temp = temp.prev
				temp2 = temp2.prev
			}
			if result < 0 {
				result += 10
				carry = 1
			} else {
				carry = 0
			}
			tempDoublyList.AddFrontNodeDLL(result)
		}

		temp = tempDoublyList.head

		if carry > 0 {
			for temp != nil {
				if temp.prev == nil {
					d3.AddFrontNodeDLL((9 - temp.data) * -1)
					temp = temp.next
				} else if temp.next == nil {
					d3.AddEndNodeDLL(10 - temp.data)
					temp = temp.next
				} else {
					d3.AddEndNodeDLL(9 - temp.data)
					temp = temp.next
				}
			}
		} else {
			for temp != nil {
				d3.AddEndNodeDLL(temp.data)
				temp = temp.next
			}
		}
	}
	return nil
}

func main() {
	doublyList := initDoublyList()
	doublyList2 := initDoublyList()
	doublyListAddResult := initDoublyList()
	doublyListSubtractResult := initDoublyList()

	// User input for first number.
	fmt.Printf("Enter size of your first number: ")
	var size int
	fmt.Scanln(&size)
	for i := 0; i < size; i++ {
		var value int
		fmt.Printf("Enter %dth element: ", i)
		fmt.Scanf("%d%*c", &value)
		doublyList.AddEndNodeDLL(value)
	}

	// User input for second number.
	fmt.Printf("Enter size of your second number: ")
	var size2 int
	fmt.Scanln(&size2)
	for i := 0; i < size2; i++ {
		var value int
		fmt.Printf("Enter %dth element: ", i)
		fmt.Scanf("%d%*c", &value)
		doublyList2.AddEndNodeDLL(value)
	}

	err := add(doublyList, doublyList2, doublyListAddResult)
	if err != nil {
		fmt.Println(err.Error())
	}

	err = subtract(doublyList, doublyList2, doublyListSubtractResult)
	if err != nil {
		fmt.Println(err.Error())
	}

	print("First list:						")
	err = doublyList.TraverseForward()
	if err != nil {
		fmt.Println(err.Error())
	}

	print("Second list:						")
	err = doublyList2.TraverseForward()
	if err != nil {
		fmt.Println(err.Error())
	}

	print("Result after adding them together:			")
	err = doublyListAddResult.TraverseForward()
	if err != nil {
		fmt.Println(err.Error())
	}

	print("Result after subtracting them:				")
	err = doublyListSubtractResult.TraverseForward()
	if err != nil {
		fmt.Println(err.Error())
	}
}
