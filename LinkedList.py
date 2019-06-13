from Crypto.Cipher import AES
from Crypto.Hash import SHA256


class Node:
    CIPHER_MODE = AES.MODE_EAX

    def __init__(self, data, key, next=None):
        cipher = AES.new(key, self.CIPHER_MODE)
        self.data, self.tag = cipher.encrypt_and_digest(data.encode('unicode_escape'))
        self.nonce = cipher.nonce
        self.chain = None
        self.prev_hash = None
        self.next = next

    def update(self, node):
        hash_object = SHA256.new(self.data)
        node.chain = self.chain
        self.chain = node

        node.prev_hash = self.prev_hash
        self.data, node.data = node.data, self.data
        self.tag, node.tag = node.tag, self.tag
        self.nonce, node.nonce = node.nonce, self.nonce

        self.prev_hash = hash_object.digest()

    def print_history(self, key):
        cursor = self.chain
        while cursor:
            cursor.print(key)
            print()
            cursor = cursor.chain

    def print(self, key):
        cipher = AES.new(key, self.CIPHER_MODE, self.nonce)
        try:
            decrypted_data = cipher.decrypt(self.data)
            print(decrypted_data.decode("unicode_escape"), end=' ')
        except UnicodeDecodeError:
            print('#-unauthorized-#')


class LinkedList:

    def __init__(self):
        self.head = None
        self.length = 0

    def print(self, key):
        if not self.head:
            return

        cursor = self.head
        while cursor:
            cursor.print(key)
            cursor = cursor.next

        print()

    def push(self, data, key):

        if not self.head:
            self.head = Node(data, key)
            self.length += 1
            return

        cursor = self.head

        while cursor.next:
            cursor = cursor.next

        cursor.next = Node(data, key)
        self.length += 1

    def update(self, data, key, position):
        if position > self.length:
            print(f'There is no node at position {position}')
            return

        if position == self.length:
            self.push(data, key)
            return

        cursor = self.head

        while position != 0:
            cursor = cursor.next
            position -= 1

        node = Node(data, key)
        cursor.update(node)

    def print_node_history(self, key, position):

        if position > self.length:
            print(f'There is no node at position {position}')
            return

        cursor = self.head

        while position != 0:
            cursor = cursor.next
            position -= 1

        cursor.print_history(key)

    def verify_node_integrity(self, position):

        if position > self.length:
            print(f'There is no node at position {position}')
            return

        cursor = self.head

        while position != 0:
            cursor = cursor.next
            position -= 1

        while cursor.chain:
            hash_object = SHA256.new(cursor.chain.data)
            if hash_object.digest() != cursor.prev_hash:
                print('Node Integrity Violated \u2715')
            else:
                print('Node Integrity Satisfaction \u2713')
            cursor = cursor.chain
